package co.com.cargame;

import co.com.cargame.entity.Carril;
import co.com.sofka.domain.generic.AggregateEvent;

import java.util.ArrayList;

public class Pista extends AggregateEvent<PistaId> {

    private ArrayList<Carril> carriles = new ArrayList<>();

    public Pista(PistaId entityId) {
        super(entityId);
    }


}
