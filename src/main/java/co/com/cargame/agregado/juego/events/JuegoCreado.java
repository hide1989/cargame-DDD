package co.com.cargame.agregado.juego.events;

import co.com.sofka.domain.generic.DomainEvent;

public class JuegoCreado extends DomainEvent {

    private final Integer cantidadCarros;

    private final Integer cantidadCarriles;

    private final Integer longitudPista;

    public JuegoCreado(Integer cantidadCarros, Integer longitudPista) {
        super("com.cargame.juegoCreado");
        this.cantidadCarros = cantidadCarros;
        this.cantidadCarriles = cantidadCarros;
        this.longitudPista = longitudPista;
    }

    public int getCantidadCarros() {
        return cantidadCarros;
    }

    public int getCantidadCarriles() {
        return cantidadCarriles;
    }

    public Integer getLongitudPista() {
        return longitudPista;
    }

}
