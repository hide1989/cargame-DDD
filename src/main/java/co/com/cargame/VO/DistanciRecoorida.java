package co.com.cargame.VO;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class DistanciRecoorida implements ValueObject<Integer> {

    private final Integer distaciaRecorrida;

    public DistanciRecoorida(Integer distaciaRecorrida){
        this.distaciaRecorrida = Objects.requireNonNull(distaciaRecorrida, "La distancia no puede ser null");
    }

    @Override
    public Integer value() {
        return distaciaRecorrida;
    }
}
