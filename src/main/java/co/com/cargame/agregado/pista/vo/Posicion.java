package co.com.cargame.agregado.pista.vo;

import co.com.sofka.domain.generic.ValueObject;

public class Posicion implements ValueObject<Integer> {

    private Integer puesto;

    public Posicion(Integer puesto){
        this.puesto = puesto;
    }

    @Override
    public Integer value() {
        return puesto;
    }
}
