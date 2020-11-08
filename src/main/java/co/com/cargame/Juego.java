package co.com.cargame;

import co.com.cargame.agregado.juego.events.*;
import co.com.cargame.agregado.juego.vo.*;
import co.com.cargame.agregado.juego.Carro;
import co.com.cargame.agregado.juego.Conductor;
import co.com.cargame.agregado.pista.vo.PistaId;
import co.com.cargame.agregado.pista.vo.Podium;
import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.ArrayList;
import java.util.List;

public class Juego extends AggregateEvent<JuegoID> {

    protected ArrayList<Carro> listaCarros;
    protected  Integer limiteCarros;
    protected  Boolean isCarreraIniciada;

    public Juego(JuegoID entityId, Integer cantidadCarros, Integer longitudPista) {
        super(entityId);
        appendChange(new JuegoCreado(cantidadCarros, longitudPista)).apply();
    }

    private Juego(JuegoID entityId){
        super(entityId);
        subscribe(new JuegoState(this));
    }

    public static Juego from(JuegoID juegoID, List<DomainEvent> events){
        var juego = new Juego(juegoID);
        events.forEach(juego::applyEvent);
        return  juego;
    }

    public void agregarCarro(CarroId carroId, Color color, Modelo modelo, Conductor conductor, DistanciRecoorida distanciRecoorida){
        appendChange( new CarroAgregado(carroId, color, modelo, conductor, distanciRecoorida)).apply();
    }

    public void iniciarCarrera(JuegoID juegoID, ArrayList<Carro> listaCarros, PistaId pistaId, Integer longitudPista){
        appendChange(new CarreraIniciada(true, juegoID, listaCarros, pistaId, longitudPista)).apply();
    }

    public void moverCarros(ArrayList<Carro> listaCarros, PistaId pistaId, Integer longitudPista, JuegoID juegoID){
        appendChange(new CarrosDesplazados(listaCarros, pistaId, longitudPista, juegoID)).apply();
    }

    public void verPodio(Podium podio, JuegoID juegoID){
        appendChange(new PodioVisto(podio, juegoID)).apply();
    }

}
