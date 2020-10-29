package co.com.cargame.VO;

import co.com.sofka.domain.generic.ValueObject;

public class Podium implements ValueObject<String> {

    private String primerPuesto;
    private String segundoPuesto;
    private String tercerPuesto;

    public Podium(String primerPuesto, String segundoPuesto, String tercerPuesto){
        this.primerPuesto = primerPuesto;
        this.segundoPuesto = segundoPuesto;
        this.tercerPuesto = tercerPuesto;
    }

    public String value() {
        return null;
    }
}
