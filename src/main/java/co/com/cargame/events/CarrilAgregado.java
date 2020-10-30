package co.com.cargame.events;

import co.com.cargame.PistaId;
import co.com.cargame.entity.Carro;
import co.com.sofka.domain.generic.DomainEvent;

public class CarrilAgregado extends DomainEvent {

    private final Integer longitudPista;
    private final Carro carro;

    public CarrilAgregado(Integer longitudPista, Carro carro){
        super("com.cargame.carrilAgregado");
        this.longitudPista = longitudPista;
        this.carro = carro;
    }

    public Integer getLongitudPista() {
        return longitudPista;
    }


    public Carro getCarro() {
        return carro;
    }


}
