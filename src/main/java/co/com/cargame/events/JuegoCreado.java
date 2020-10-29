package co.com.cargame.events;

import co.com.sofka.domain.generic.DomainEvent;

public class JuegoCreado extends DomainEvent {

    public int getCantidadCarros() {
        return cantidadCarros;
    }

    private final int cantidadCarros;

    public JuegoCreado(int cantidadCarros) {
        super("com.cargame.juegoCreado");
        this.cantidadCarros = cantidadCarros;
    }
}
