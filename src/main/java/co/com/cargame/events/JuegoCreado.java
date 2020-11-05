package co.com.cargame.events;

import co.com.sofka.domain.generic.DomainEvent;

public class JuegoCreado extends DomainEvent {

    private final int cantidadCarros;

    private final int cantidadCarriles;

    public JuegoCreado(int cantidadCarros) {
        super("com.cargame.juegoCreado");
        this.cantidadCarros = cantidadCarros;
        this.cantidadCarriles = cantidadCarros;
    }

    public int getCantidadCarros() {
        return cantidadCarros;
    }

    public int getCantidadCarriles() {
        return cantidadCarriles;
    }


}
