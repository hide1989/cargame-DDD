package co.com.cargame.events;

import co.com.cargame.VO.DistanciRecoorida;
import co.com.cargame.VO.JuegoID;
import co.com.cargame.entity.Carro;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.generic.Incremental;

import java.util.ArrayList;

public class CarroDesplazado extends DomainEvent {

    private ArrayList<Carro> listaCarros;
    private JuegoID juegoID;

    public CarroDesplazado( ArrayList<Carro> listaCarros, JuegoID juegoID){
        super("com.cargame.carrodesplazado");
        this.listaCarros = listaCarros;
        this.juegoID = juegoID;
    }

    public ArrayList<Carro> getListaCarros() {
        return listaCarros;
    }

    public JuegoID getJuegoID() {
        return juegoID;
    }

}
