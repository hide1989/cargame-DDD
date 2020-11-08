package co.com.cargame.agregado.juego.vo;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Random;

public class Dado implements ValueObject<Integer> {

    public final int value;

    public Dado(){
        Random random = new Random();
        int numeroAleatorio = random.nextInt(6);
        this.value =  numeroAleatorio+1;
    }

    @Override
    public Integer value() {
        return value;
    }
}
