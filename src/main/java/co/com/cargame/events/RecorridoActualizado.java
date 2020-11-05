package co.com.cargame.events;

import co.com.cargame.VO.JuegoID;
import co.com.cargame.VO.PistaId;
import co.com.cargame.entity.Carro;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.ArrayList;

public class RecorridoActualizado extends DomainEvent {

    private ArrayList<Carro> listaCarros;
    private PistaId pistaId;

    private Integer longitudCarril;

    public RecorridoActualizado(ArrayList<Carro> listaCarros, Integer longitudCarril, PistaId pistaId){
        super("com.cargame.recorridoactualizado");
        this.listaCarros = listaCarros;
        this.pistaId = pistaId;
        this.longitudCarril = longitudCarril;
    }

    public ArrayList<Carro> getListaCarros() {
        return listaCarros;
    }

    public PistaId getPistaId() {
        return pistaId;
    }

    public Integer getLongitudCarril() {
        return longitudCarril;
    }
}
