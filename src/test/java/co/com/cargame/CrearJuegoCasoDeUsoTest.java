package co.com.cargame;

import co.com.cargame.agregado.juego.events.CarreraIniciada;
import co.com.cargame.agregado.juego.events.CarrosDesplazados;
import co.com.cargame.agregado.juego.events.JuegoCreado;
import co.com.cargame.agregado.juego.events.PodioVisto;
import co.com.cargame.agregado.juego.vo.*;
import co.com.cargame.agregado.pista.events.GanadoresIdentificados;
import co.com.cargame.agregado.pista.events.PodioCreado;
import co.com.cargame.agregado.pista.events.RecorridoActualizado;
import co.com.cargame.agregado.pista.vo.PistaId;
import co.com.cargame.agregado.pista.vo.Podium;
import co.com.cargame.commands.ConfigurarJuego;
import co.com.cargame.commands.IniciarCarrera;
import co.com.cargame.agregado.juego.Carro;
import co.com.cargame.agregado.juego.Conductor;
import co.com.cargame.usecase.*;
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
        ArrayList<Carro> listaCarros = crearCarros(0,0,0);

        ConfigurarJuego configurarJuego = new ConfigurarJuego(listaCarros, listaCarros.size(), 1000);
        UseCaseHandler.getInstance().asyncExecutor(crearJuegoCasoDeUso, new RequestCommand<>(configurarJuego))
                .subscribe(subscriber);
        verify(subscriber, times(4)).onNext(eventCaptor.capture());
        JuegoCreado juegoCreado = (JuegoCreado) eventCaptor.getAllValues().get(0);

        Assertions.assertEquals(3, juegoCreado.getCantidadCarros());
        Assertions.assertEquals(3, juegoCreado.getCantidadCarriles());
        Assertions.assertEquals(1000, juegoCreado.getLongitudPista());
        Assertions.assertEquals(4, eventCaptor.getAllValues().size());
    }

    @Test
    void InicioDelJuego(){
        IniciarCarreraCasoDeUso iniciarCarreraCasoDeUso = new IniciarCarreraCasoDeUso();
        ArrayList<Carro> listaCarros = crearCarros(0,0,0);

        IniciarCarrera iniciarCarrera = new IniciarCarrera(new JuegoID("xxx"), listaCarros, new PistaId("aaa"), 1000);

        when(repository.getEventsBy(anyString())).thenReturn(List.of(
                new JuegoCreado(3, 1000),
                new CarreraIniciada(true, new JuegoID("xxx"),listaCarros, new PistaId("aaa"), 1000)
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
        ArrayList<Carro> listaCarros = crearCarros(0,0,0);

        CarreraIniciada carreraIniciada = new CarreraIniciada(true, new JuegoID("xxx"), listaCarros, new PistaId("aaa"), 1000);

        when(repository.getEventsBy(anyString())).thenReturn(List.of(
                new JuegoCreado(3, 1000),
                new CarreraIniciada(true, new JuegoID("xxx"), listaCarros, new PistaId("aaa"), 1000),
                new CarrosDesplazados(listaCarros, new PistaId("aaa"), 1000, new JuegoID("xxx"))
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

    @Test
    void ActualizarRecorridoTest(){

        AdministrarCarreraCasoDeUso administrarCarreraCasoDeUso = new AdministrarCarreraCasoDeUso();
        ArrayList<Carro> listaCarros = crearCarros(200, 300, 600);

        CarrosDesplazados carrosDesplazados = new CarrosDesplazados(listaCarros, new PistaId("aaa"), 1000, new JuegoID("xxx"));

        when(repository.getEventsBy(anyString())).thenReturn(List.of(
                new JuegoCreado(3, 1000),
                new CarreraIniciada(true, new JuegoID("xxx"), listaCarros, new PistaId("aaa"), 1000)
        ));

        administrarCarreraCasoDeUso.addRepository(repository);

        UseCaseHandler.getInstance()
                .setIdentifyExecutor("aaa")
                .asyncExecutor(administrarCarreraCasoDeUso, new TriggeredEvent<>(carrosDesplazados))
                .subscribe(subscriber);

        verify(subscriber, times(2)).onNext(eventCaptor.capture());
        var event = eventCaptor.getAllValues();
        Assertions.assertTrue( event.get(0) instanceof RecorridoActualizado);
    }

    @Test
    void IdentificarGanadoresTest(){

        AdministrarCarreraCasoDeUso administrarCarreraCasoDeUso = new AdministrarCarreraCasoDeUso();
        ArrayList<Carro> listaCarros = crearCarros(1100, 1200, 1300);

        CarrosDesplazados carrosDesplazados = new CarrosDesplazados(listaCarros, new PistaId("aaa"), 1000, new JuegoID("xxx"));

        when(repository.getEventsBy(anyString())).thenReturn(List.of(
                new JuegoCreado(3, 1000),
                new CarreraIniciada(true, new JuegoID("xxx"), listaCarros, new PistaId("aaa"), 1000)
        ));

        administrarCarreraCasoDeUso.addRepository(repository);

        UseCaseHandler.getInstance()
                .setIdentifyExecutor("aaa")
                .asyncExecutor(administrarCarreraCasoDeUso, new TriggeredEvent<>(carrosDesplazados))
                .subscribe(subscriber);

        verify(subscriber, times(3)).onNext(eventCaptor.capture());
        var event = eventCaptor.getAllValues();
        Assertions.assertTrue( event.get(1) instanceof GanadoresIdentificados);
    }

    @Test
    void AsignarPodioTest(){

        AdministrarCarreraCasoDeUso administrarCarreraCasoDeUso = new AdministrarCarreraCasoDeUso();
        ArrayList<Carro> listaCarros = crearCarros(1100, 1200, 1300);

        CarrosDesplazados carrosDesplazados = new CarrosDesplazados(listaCarros, new PistaId("aaa"), 1000, new JuegoID("xxx"));

        when(repository.getEventsBy(anyString())).thenReturn(List.of(
                new JuegoCreado(3, 1000),
                new CarreraIniciada(true, new JuegoID("xxx"), listaCarros, new PistaId("aaa"), 1000)
        ));

        administrarCarreraCasoDeUso.addRepository(repository);

        UseCaseHandler.getInstance()
                .setIdentifyExecutor("aaa")
                .asyncExecutor(administrarCarreraCasoDeUso, new TriggeredEvent<>(carrosDesplazados))
                .subscribe(subscriber);

        verify(subscriber, times(3)).onNext(eventCaptor.capture());
        var event = eventCaptor.getAllValues();
        Assertions.assertTrue( event.get(2) instanceof PodioCreado);
    }

    @Test
    void MostrarPodioTest(){
        VerPodioCasoDeUso verPodioCasoDeUso = new VerPodioCasoDeUso();
        ArrayList<Carro> listaCarros = crearCarros(1100, 1200, 1300);

        PodioCreado podioCreado = new PodioCreado(new PistaId("aaa"), listaCarros, new JuegoID("xxx"));


        when(repository.getEventsBy(anyString())).thenReturn(List.of(
                new JuegoCreado(3, 1000),
                new CarreraIniciada(true, new JuegoID("xxx"), listaCarros, new PistaId("aaa"), 1000),
                new PodioVisto(new Podium(
                        listaCarros.get(0).conductor().nombre(),
                        listaCarros.get(1).conductor().nombre(),
                        listaCarros.get(2).conductor().nombre()
                ), new JuegoID("xxx"))
        ));

        verPodioCasoDeUso.addRepository(repository);

        UseCaseHandler.getInstance()
                .setIdentifyExecutor("xxx")
                .asyncExecutor(verPodioCasoDeUso, new TriggeredEvent<>(podioCreado))
                .subscribe(subscriber);

        verify(subscriber).onNext(eventCaptor.capture());
    }



    private ArrayList<Carro> crearCarros(Integer distancia1, Integer distancia2, Integer distancia3){
        ArrayList<Carro> listaCarros = new ArrayList<>();
        listaCarros.add(new Carro(
                new CarroId("ABC123"),
                new Modelo("2000"),
                new Color("Rojo"),
                new Conductor(new ConductorId("123456"), "David" ),
                new DistanciRecoorida(distancia1)));
        listaCarros.add(new Carro(
                new CarroId("ABC897"),
                new Modelo("2010"),
                new Color("Azul"),
                new Conductor(new ConductorId("21648496"), "Raul" ),
                new DistanciRecoorida(distancia2)));
        listaCarros.add(new Carro(
                new CarroId("WGR898"),
                new Modelo("2015"),
                new Color("Negro"),
                new Conductor(new ConductorId("156481694"), "Sara" ),
                new DistanciRecoorida(distancia3)));

        return listaCarros;
    }

}