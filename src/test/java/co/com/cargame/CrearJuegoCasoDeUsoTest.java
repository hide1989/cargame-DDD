package co.com.cargame;

import co.com.cargame.VO.*;
import co.com.cargame.commands.ConfigurarJuego;
import co.com.cargame.commands.IniciarCarrera;
import co.com.cargame.entity.Carro;
import co.com.cargame.entity.Conductor;
import co.com.cargame.events.CarreraIniciada;
import co.com.cargame.events.CarrosDesplazados;
import co.com.cargame.events.JuegoCreado;
import co.com.cargame.usecase.CrearJuegoCasoDeUso;
import co.com.cargame.usecase.DesplazarCarrosCasoDeUso;
import co.com.cargame.usecase.IniciarCarreraCasoDeUso;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.TriggeredEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class CrearJuegoCasoDeUsoTest extends UseCaseHandleBaseTest {

    @Test
    void crearJuego(){
        CrearJuegoCasoDeUso crearJuegoCasoDeUso = new CrearJuegoCasoDeUso();
        ArrayList<Carro> listaCarros = new ArrayList<>();
        listaCarros.add(new Carro(
                new CarroId("ABC123"),
                new Modelo("2000"),
                new Color("Rojo"),
                new Conductor(new ConductorId("123456"), "David" )));
        listaCarros.add(new Carro(
                new CarroId("ABC897"),
                new Modelo("2010"),
                new Color("Azul"),
                new Conductor(new ConductorId("21648496"), "Raul" )));

        ConfigurarJuego configurarJuego = new ConfigurarJuego(listaCarros, listaCarros.size(), 1000);
        UseCaseHandler.getInstance().asyncExecutor(crearJuegoCasoDeUso, new RequestCommand<>(configurarJuego))
                .subscribe(subscriber);
        verify(subscriber, times(3)).onNext(eventCaptor.capture());
        JuegoCreado juegoCreado = (JuegoCreado) eventCaptor.getAllValues().get(0);

        Assertions.assertEquals(2, juegoCreado.getCantidadCarros());
        Assertions.assertEquals(2, juegoCreado.getCantidadCarriles());
        Assertions.assertEquals(3, eventCaptor.getAllValues().size());
    }

    @Test
    void InicioDelJuego(){
        IniciarCarreraCasoDeUso iniciarCarreraCasoDeUso = new IniciarCarreraCasoDeUso();
        ArrayList<Carro> listaCarros = new ArrayList<>();
        listaCarros.add(new Carro(
                new CarroId("ABC123"),
                new Modelo("2000"),
                new Color("Rojo"),
                new Conductor(new ConductorId("123456"), "David" )));
        listaCarros.add(new Carro(
                new CarroId("ABC897"),
                new Modelo("2010"),
                new Color("Azul"),
                new Conductor(new ConductorId("21648496"), "Raul" )));
        IniciarCarrera iniciarCarrera = new IniciarCarrera(new JuegoID("xxx"), listaCarros);

        when(repository.getEventsBy(anyString())).thenReturn(List.of(
                new JuegoCreado(2),
                new CarreraIniciada(true, new JuegoID("xxx"),listaCarros)
        ));

        iniciarCarreraCasoDeUso.addRepository(repository);

        UseCaseHandler.getInstance()
                .setIdentifyExecutor("xxx")
                .asyncExecutor(iniciarCarreraCasoDeUso, new RequestCommand<>(iniciarCarrera))
                .subscribe(subscriber);

        verify(subscriber).onNext(eventCaptor.capture());
        CarreraIniciada carreraIniciada = (CarreraIniciada) eventCaptor.getValue();

        Assertions.assertEquals(true, carreraIniciada.isCarreraIniciada());
    }

    @Test
    void MoverCarroCaseTest(){
        DesplazarCarrosCasoDeUso desplazarCarrosCasoDeUso = new DesplazarCarrosCasoDeUso();
        ArrayList<Carro> listaCarros = new ArrayList<>();

        listaCarros.add(new Carro(
                new CarroId("ABC123"),
                new Modelo("2000"),
                new Color("Rojo"),
                new Conductor(new ConductorId("123456"), "David" )));
        listaCarros.add(new Carro(
                new CarroId("ABC897"),
                new Modelo("2010"),
                new Color("Azul"),
                new Conductor(new ConductorId("21648496"), "Raul" )));

        CarreraIniciada carreraIniciada = new CarreraIniciada(true, new JuegoID("xxx"), listaCarros );

        when(repository.getEventsBy(anyString())).thenReturn(List.of(
                new JuegoCreado(2),
                new CarreraIniciada(true, new JuegoID("xxx"), listaCarros),
                new CarrosDesplazados(listaCarros, new JuegoID("xxx"))
        ));

        desplazarCarrosCasoDeUso.addRepository(repository);

        UseCaseHandler.getInstance()
                .setIdentifyExecutor("xxx")
                .asyncExecutor(desplazarCarrosCasoDeUso, new TriggeredEvent<>(carreraIniciada))
                .subscribe(subscriber);

        verify(subscriber).onNext(eventCaptor.capture());
        var events = eventCaptor.getValue();
        Assertions.assertTrue(events instanceof CarrosDesplazados);
    }

}