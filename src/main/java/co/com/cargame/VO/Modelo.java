package co.com.cargame.VO;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Modelo implements ValueObject<String> {

    private final String modelo;

    public Modelo(String modelo){
        this.modelo = Objects.requireNonNull(modelo, "El modelo no puede estar null");
    }

    @Override
    public String value() {
        return null;
    }
}
