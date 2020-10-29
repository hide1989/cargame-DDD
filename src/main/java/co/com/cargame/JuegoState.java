package co.com.cargame;

import co.com.cargame.entity.Carro;
import co.com.cargame.events.CarroAgregado;
import co.com.cargame.events.JuegoCreado;
import co.com.sofka.domain.generic.EventChange;

import java.util.ArrayList;

public class JuegoState extends EventChange {

    public JuegoState(Juego juego){

        apply((CarroAgregado carroAgregado)->{
            if(juego.limiteCarros > juego.listaCarros.size() ){
                juego.listaCarros.add(new Carro(carroAgregado.getCarroId(), carroAgregado.getModelo(), carroAgregado.getColor(), carroAgregado.getConductor() ));
            }else{
                throw new IllegalArgumentException("la cantidad de carros es mayor a la permitida");
            }
        });

        apply((JuegoCreado event) ->{
            juego.listaCarros = new ArrayList<>(event.getCantidadCarros());
            juego.limiteCarros = event.getCantidadCarros();
        });
    }
}
