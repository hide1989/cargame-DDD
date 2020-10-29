package co.com.cargame;

import co.com.cargame.VO.CarroId;
import co.com.cargame.VO.Color;
import co.com.cargame.VO.ConductorId;
import co.com.cargame.VO.Modelo;
import co.com.cargame.commands.ConfigurarJuego;
import co.com.cargame.entity.Carro;
import co.com.cargame.entity.Conductor;
import co.com.cargame.events.JuegoCreado;
import co.com.cargame.usecase.CrearJuegoCasoDeUso;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

        ConfigurarJuego configurarJuego = new ConfigurarJuego(listaCarros, listaCarros.size());
        UseCaseHandler.getInstance().asyncExecutor(crearJuegoCasoDeUso, new RequestCommand<>(configurarJuego))
                .subscribe(subscriber);
        verify(subscriber, times(3)).onNext(eventCaptor.capture());
        JuegoCreado juegoCreado = (JuegoCreado) eventCaptor.getAllValues().get(0);

        Assertions.assertEquals(2, juegoCreado.getCantidadCarros());
        Assertions.assertEquals(3, eventCaptor.getAllValues().size());
    }


}