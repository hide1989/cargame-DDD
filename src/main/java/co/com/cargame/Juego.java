package co.com.cargame;

import co.com.cargame.VO.CarroId;
import co.com.cargame.VO.Color;
import co.com.cargame.VO.JuegoID;
import co.com.cargame.VO.Modelo;
import co.com.cargame.entity.Carro;
import co.com.cargame.entity.Conductor;
import co.com.cargame.events.CarroAgregado;
import co.com.cargame.events.JuegoCreado;
import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.ArrayList;
import java.util.List;

public class Juego extends AggregateEvent<JuegoID> {

    protected ArrayList<Carro> listaCarros;
    protected  Integer limiteCarros;

    public Juego(JuegoID entityId, int cantidadCarros) {
        super(entityId);
        appendChange(new JuegoCreado(cantidadCarros)).apply();
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

    public void agregarCarro(CarroId carroId, Color color, Modelo modelo, Conductor conductor){
        appendChange( new CarroAgregado(carroId, color, modelo, conductor)).apply();
    }
}
