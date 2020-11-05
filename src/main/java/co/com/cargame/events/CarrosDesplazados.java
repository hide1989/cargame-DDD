package co.com.cargame.events;


import co.com.cargame.VO.JuegoID;
import co.com.cargame.VO.PistaId;
import co.com.cargame.entity.Carro;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.generic.Incremental;

import java.util.ArrayList;

public class CarrosDesplazados extends DomainEvent implements Incremental {

    private ArrayList<Carro> listaCarros;
    private JuegoID juegoID;
    private  PistaId pistaId;

    //TODO: CON UN CONSTRUCTOR VACIO BASTA
    public CarrosDesplazados(ArrayList<Carro> listaCarros, JuegoID juegoID){
        super("com.cargame.carrosdesplazados");
        this.listaCarros = listaCarros;
        this.juegoID = juegoID;
    }

    public ArrayList<Carro> getListaCarros() {
        return listaCarros;
    }

    public JuegoID getJuegoID() {
        return juegoID;
    }

    public PistaId getPistaId() {
        return pistaId;
    }


}
