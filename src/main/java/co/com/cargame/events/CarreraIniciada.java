package co.com.cargame.events;

import co.com.cargame.VO.Carril;
import co.com.cargame.VO.JuegoID;
import co.com.cargame.VO.PistaId;
import co.com.cargame.entity.Carro;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.ArrayList;

public class CarreraIniciada extends DomainEvent {

    private Boolean isCarreraIniciada;
    private JuegoID juegoID;

    private ArrayList<Carro> listaCarros;

    public CarreraIniciada(Boolean isCarreraIniciada, JuegoID juegoID, ArrayList<Carro> listaCarros){
        super("com.cargame.carrerainiciada");
        this.isCarreraIniciada = isCarreraIniciada;
        this.juegoID = juegoID;
        this.listaCarros = listaCarros;
    }

    public boolean isCarreraIniciada() {
        return isCarreraIniciada;
    }

    public JuegoID getJuegoID() {
        return juegoID;
    }

    public ArrayList<Carro> getListaCarros() {
        return listaCarros;
    }

}
