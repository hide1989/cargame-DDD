package co.com.cargame.VO;

import co.com.sofka.domain.generic.Identity;

public class PistaId extends Identity {

    public PistaId(String pistaId){
        super(pistaId);
    }

    public PistaId(){

    }

    public static PistaId of(String pistaId){
        return new PistaId(pistaId);
    }
}
