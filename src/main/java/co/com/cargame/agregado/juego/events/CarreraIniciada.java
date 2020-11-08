package co.com.cargame.agregado.juego.events;

import co.com.cargame.agregado.juego.vo.JuegoID;
import co.com.cargame.agregado.pista.vo.PistaId;
import co.com.cargame.agregado.juego.Carro;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.ArrayList;

public class CarreraIniciada extends DomainEvent {

    private Boolean isCarreraIniciada;
    private JuegoID juegoID;
    private ArrayList<Carro> listaCarros;
    private Integer longitudPista;
    private PistaId pistaId;

    public CarreraIniciada(Boolean isCarreraIniciada, JuegoID juegoID, ArrayList<Carro> listaCarros, PistaId pistaId, Integer longitudPista){
        super("com.cargame.carrerainiciada");
        this.isCarreraIniciada = isCarreraIniciada;
        this.juegoID = juegoID;
        this.listaCarros = listaCarros;
        this.pistaId = pistaId;
        this.longitudPista = longitudPista;
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

    public PistaId getPistaId() {
        return pistaId;
    }

    public Integer getLongitudPista() {
        return longitudPista;
    }


}
