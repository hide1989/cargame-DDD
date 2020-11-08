package co.com.cargame.agregado.juego.events;

import co.com.cargame.agregado.juego.vo.JuegoID;
import co.com.cargame.agregado.pista.vo.Podium;
import co.com.sofka.domain.generic.DomainEvent;

public class PodioVisto extends DomainEvent {

    private final Podium podium;
    private final JuegoID juegoID;

    public PodioVisto(Podium podio, JuegoID juegoID){
        super("com.cargame.podiovisto");
        this.juegoID = juegoID;
        this.podium = podio;
    }

    public Podium getPodium() {
        return podium;
    }

    public JuegoID getJuegoID() {
        return juegoID;
    }
}
