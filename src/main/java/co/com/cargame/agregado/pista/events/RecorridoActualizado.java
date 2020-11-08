package co.com.cargame.agregado.pista.events;

import co.com.cargame.agregado.pista.vo.PistaId;
import co.com.cargame.agregado.juego.Carro;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.ArrayList;

public class RecorridoActualizado extends DomainEvent {

    private ArrayList<Carro> listaCarros;
    private PistaId pistaId;

    public RecorridoActualizado(ArrayList<Carro> listaCarros, PistaId pistaId){
        super("com.cargame.recorridoactualizado");
        this.listaCarros = listaCarros;
        this.pistaId = pistaId;
    }

    public ArrayList<Carro> getListaCarros() {
        return listaCarros;
    }

    public PistaId getPistaId() {
        return pistaId;
    }
}
