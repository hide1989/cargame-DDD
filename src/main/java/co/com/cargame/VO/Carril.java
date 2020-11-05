package co.com.cargame.VO;
import co.com.cargame.entity.Carro;
import co.com.sofka.domain.generic.ValueObject;

public class Carril implements ValueObject<String> {

    private final Integer longitudCarril;
    private final Carro carro;

    public Carril(Integer longitudCarril, Carro carro){
        this.longitudCarril = longitudCarril;
        this.carro = carro;
    }

    @Override
    public String value() {
        return null;
    }
}
