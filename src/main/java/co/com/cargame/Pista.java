package co.com.cargame;

import co.com.cargame.VO.PistaId;
import co.com.cargame.VO.Carril;
import co.com.cargame.entity.Carro;
import co.com.cargame.events.CarrilAgregado;
import co.com.cargame.events.JuegoCreado;
import co.com.cargame.events.RecorridoActualizado;
import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.ArrayList;
import java.util.List;

public class Pista extends AggregateEvent<PistaId> {

    protected ArrayList<Carril> listaCarriles = new ArrayList<>();

    public Pista(PistaId entityId, int cantidadCarros) {
        super(entityId);
        appendChange(new JuegoCreado(cantidadCarros)).apply();
    }

    public Pista(PistaId pistaId){
        super(pistaId);
        subscribe(new JuegoState(this));
    }

    public static Pista from(PistaId pistaId, List<DomainEvent> events){
        var pista = new Pista(pistaId);
        events.forEach(pista::applyEvent);
        return  pista;
    }

    public void agregarCarril(Integer longitudCarril, Carro carro){
        appendChange(new CarrilAgregado(longitudCarril, carro)).apply();
    }

    public void actualizarRecorrido(ArrayList<Carro> listaCarros, Integer longitudCarril, PistaId pistaId){
        appendChange(new RecorridoActualizado(listaCarros, longitudCarril, pistaId));
    }

    public void asignarPodioPorDistancia(ArrayList<Carril> listaCarriles){

    }

}
