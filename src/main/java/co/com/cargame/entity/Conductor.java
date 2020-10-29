package co.com.cargame.entity;

import co.com.cargame.VO.ConductorId;
import co.com.cargame.VO.Dado;
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
}
