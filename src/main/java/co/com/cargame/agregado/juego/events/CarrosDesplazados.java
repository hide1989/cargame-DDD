package co.com.cargame.agregado.juego.events;

import co.com.cargame.agregado.juego.vo.JuegoID;
import co.com.cargame.agregado.pista.vo.PistaId;
import co.com.cargame.agregado.juego.Carro;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.generic.Incremental;

import java.util.ArrayList;

public class CarrosDesplazados extends DomainEvent implements Incremental {

    private ArrayList<Carro> listaCarros;
    private PistaId pistaId;
    private JuegoID juegoID;
    private Integer longitudPista;

    public CarrosDesplazados(ArrayList<Carro> listaCarros, PistaId pistaId, Integer longitudPista, JuegoID juegoID){
        super("com.cargame.carrosdesplazados");
        this.listaCarros = listaCarros;
        this.pistaId = pistaId;
        this.longitudPista = longitudPista;
        this.juegoID = juegoID;
    }

    public ArrayList<Carro> getListaCarros() {
        return listaCarros;
    }


    public PistaId getPistaId() {
        return pistaId;
    }

    public Integer getLongitudPista() {
        return longitudPista;
    }

    public JuegoID getJuegoID() {
        return juegoID;
    }
}
