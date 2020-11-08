package co.com.cargame.agregado.pista.vo;
import co.com.cargame.agregado.juego.Carro;
import co.com.sofka.domain.generic.ValueObject;

public class Carril implements ValueObject<Carro> {

    private final Carro carro;

    public Carril(Carro carro){
        this.carro = carro;
    }

    @Override
    public Carro value() {
        return carro;
    }
}
