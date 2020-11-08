package co.com.cargame.agregado.pista.events;

import co.com.cargame.agregado.juego.vo.JuegoID;
import co.com.cargame.agregado.pista.vo.PistaId;
import co.com.cargame.agregado.pista.vo.Podium;
import co.com.cargame.agregado.juego.Carro;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.ArrayList;

public class PodioCreado extends DomainEvent {

    private PistaId pistaId;
    private ArrayList<Carro> ganadoresIdentificados;
    private JuegoID juegoID;
    private Podium podium;

    public PodioCreado(PistaId pistaId, ArrayList<Carro> ganadoresIdentificados, JuegoID juegoID){
        super("com.cargame.podiocreado");
        this.pistaId = pistaId;
        this.ganadoresIdentificados = ganadoresIdentificados;
        this.juegoID = juegoID;
    }

    public PistaId getPistaId() {
        return pistaId;
    }

    public ArrayList<Carro> getGanadoresIdentificados() {
        return ganadoresIdentificados;
    }

    public JuegoID getJuegoID() {
        return juegoID;
    }

}
