package co.com.cargame.entity;
import co.com.sofka.domain.generic.ValueObject;

public class Carril implements ValueObject<String> {

    private final Integer longitudPista;
    private final Integer posicion;
    private final Carro carro;

    public Carril(Integer longitudPista, Integer posicion, Carro carro){
        this.longitudPista = longitudPista;
        this.posicion = posicion;
        this.carro = carro;
    }

    @Override
    public String value() {
        return null;
    }
}
