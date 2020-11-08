package co.com.cargame.agregado.pista.events;

import co.com.cargame.agregado.pista.vo.PistaId;
import co.com.sofka.domain.generic.DomainEvent;

public class GanadoresIdentificados extends DomainEvent {

    private final PistaId pistaId;

    private final Integer longitudPista;

    public GanadoresIdentificados(PistaId pistaId, Integer longitudPista){
        super("com.cargame.ganadoresindentificados");
        this.pistaId = pistaId;
        this.longitudPista = longitudPista;
    }

    public PistaId getPistaId() {
        return pistaId;
    }

    public Integer getLongitudPista() {
        return longitudPista;
    }
}
