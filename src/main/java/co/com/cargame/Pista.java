package co.com.cargame;

import co.com.cargame.entity.Carril;
import co.com.cargame.entity.Carro;
import co.com.cargame.events.CarrilAgregado;
import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.ArrayList;
import java.util.List;

public class Pista extends AggregateEvent<PistaId> {

    protected ArrayList<Carril> listaCarriles = new ArrayList<>();

    public Pista(PistaId entityId) {
        super(entityId);
    }

    public static Pista from(PistaId pistaId, List<DomainEvent> events){
        var pista = new Pista(pistaId);
        events.forEach(pista::applyEvent);
        return  pista;
    }

    public void agregarCarril(Integer longitud, Carro carro){
        appendChange(new CarrilAgregado(longitud, carro)).apply();
    }

}
