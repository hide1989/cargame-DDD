package co.com.cargame;

import co.com.cargame.VO.Carril;
import co.com.cargame.entity.Carro;
import co.com.cargame.events.*;
import co.com.cargame.usecase.IniciarCarreraCasoDeUso;
import co.com.sofka.domain.generic.EventChange;

import java.util.ArrayList;
import java.util.logging.Logger;

public class JuegoState extends EventChange {

    private static final Logger logger = Logger.getLogger(IniciarCarreraCasoDeUso.class.getName());

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

        apply((CarreraIniciada carreraIniciada) ->{
            juego.listaCarros = carreraIniciada.getListaCarros();
            if(carreraIniciada.isCarreraIniciada()){
                juego.isCarreraIniciada = carreraIniciada.isCarreraIniciada();
            }else{
                throw new IllegalArgumentException("No se puede iniciar un juego en false");
            }
        });

        apply((CarrosDesplazados carrosDesplazados) ->{
            for (Carro listaCarro : juego.listaCarros) {
                listaCarro.avanzar();
            }
        });
    }

    public JuegoState(Pista pista){

        apply((CarrilAgregado carrilAgregado) ->{
            pista.listaCarriles.add(new Carril(carrilAgregado.getLongitudPista(), carrilAgregado.getCarro()));
        });

        apply((RecorridoActualizado recorridoActualizado) ->{
            int cantidadCarros = recorridoActualizado.getListaCarros().size();
            if(cantidadCarros <= 0){
                throw new IllegalArgumentException("No hay carros agregados");
            }
            for(int i = 0; i<= cantidadCarros-1; i++){
                pista.listaCarriles.remove(0);
                pista.listaCarriles.add(new Carril(recorridoActualizado.getLongitudCarril(),recorridoActualizado.getListaCarros().get(i)));
            }
        });

    }
}
