package co.com.cargame.commands;

import co.com.cargame.VO.Carril;
import co.com.cargame.VO.JuegoID;
import co.com.cargame.VO.PistaId;
import co.com.cargame.entity.Carro;
import co.com.sofka.domain.generic.Command;
import java.util.ArrayList;


public class IniciarCarrera implements Command {

   private JuegoID juegoID;

    private ArrayList<Carro> listaCarros;

    public IniciarCarrera(JuegoID juegoID, ArrayList<Carro> listaCarros){
        this.juegoID = juegoID;
        this.listaCarros = listaCarros;
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

}
