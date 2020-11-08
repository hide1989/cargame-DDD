package co.com.cargame.commands;

import co.com.cargame.agregado.juego.vo.JuegoID;
import co.com.cargame.agregado.pista.vo.PistaId;
import co.com.cargame.agregado.juego.Carro;
import co.com.sofka.domain.generic.Command;
import java.util.ArrayList;


public class IniciarCarrera implements Command {

    private JuegoID juegoID;
    private ArrayList<Carro> listaCarros;
    private PistaId pistaId;

    private Integer longitudPista;

    public IniciarCarrera(JuegoID juegoID, ArrayList<Carro> listaCarros, PistaId pistaId, Integer longitudPista){
        this.juegoID = juegoID;
        this.listaCarros = listaCarros;
        this.pistaId = pistaId;
        this.longitudPista = longitudPista;
    }

    public IniciarCarrera(){}

    public JuegoID getJuegoID() {
        return juegoID;
    }

    public void setJuegoID(JuegoID juegoID) {
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

}
