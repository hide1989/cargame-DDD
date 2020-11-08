package co.com.cargame.agregado.juego;

import co.com.cargame.agregado.juego.vo.ConductorId;
import co.com.cargame.agregado.juego.vo.Dado;
import co.com.sofka.domain.generic.Entity;

public class Conductor extends Entity<ConductorId> {

    private  String nombre;
    private Dado dado;

    public Conductor(ConductorId entityId, String nombre) {
        super(entityId);
        this.nombre = nombre;
    }

    public int lanzarDado(){
        dado = new Dado();
        return dado.value();
    }

    public String nombre() {
        return nombre;
    }

    public Dado dado() {
        return dado;
    }
}
