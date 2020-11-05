package co.com.cargame;

import co.com.cargame.VO.*;
import co.com.cargame.entity.Carro;
import co.com.cargame.entity.Conductor;
import co.com.cargame.events.CarreraIniciada;
import co.com.cargame.events.CarroAgregado;
import co.com.cargame.events.CarroDesplazado;
import co.com.cargame.events.JuegoCreado;
import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.ArrayList;
import java.util.List;

public class Juego extends AggregateEvent<JuegoID> {

    protected ArrayList<Carro> listaCarros;
    protected  Integer limiteCarros;
    protected  Boolean isCarreraIniciada;

    public Juego(JuegoID entityId, int cantidadCarros) {
        super(entityId);
        appendChange(new JuegoCreado(cantidadCarros)).apply();
    }

    private Juego(JuegoID entityId){
        super(entityId);
        subscribe(new JuegoState(this));
    }

    public void agregarCarro(CarroId carroId, Color color, Modelo modelo, Conductor conductor){
        appendChange( new CarroAgregado(carroId, color, modelo, conductor)).apply();
    }

    public static Juego from(JuegoID juegoID, List<DomainEvent> events){
        var juego = new Juego(juegoID);
        events.forEach(juego::applyEvent);
        return  juego;
    }

    public void iniciarCarrera(JuegoID juegoID, ArrayList<Carro> listaCarros){
        appendChange(new CarreraIniciada(true, juegoID, listaCarros )).apply();
    }

    public void moverCarros(ArrayList<Carro> listaCarros, JuegoID juegoID ){
        appendChange(new CarroDesplazado(listaCarros, juegoID)).apply();
    }

}
