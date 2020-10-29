package co.com.cargame.VO;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Color implements ValueObject<String> {

    private final String color;

    public Color(String color){
        this.color = Objects.requireNonNull(color, "El color no puede ser null");
    }

    @Override
    public String value() {
        return null;
    }
}
