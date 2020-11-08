package co.com.cargame;

import co.com.cargame.agregado.pista.events.CarrilAgregado;
import co.com.cargame.agregado.pista.events.GanadoresIdentificados;
import co.com.cargame.agregado.pista.events.PodioCreado;
import co.com.cargame.agregado.pista.events.RecorridoActualizado;
import co.com.cargame.agregado.pista.vo.Carril;
import co.com.cargame.agregado.pista.vo.Podium;
import co.com.cargame.agregado.juego.events.*;
import co.com.cargame.agregado.juego.Carro;
import co.com.cargame.usecase.IniciarCarreraCasoDeUso;
import co.com.sofka.domain.generic.EventChange;

import java.util.ArrayList;
import java.util.logging.Logger;

public class JuegoState extends EventChange {

    private static final Logger logger = Logger.getLogger(IniciarCarreraCasoDeUso.class.getName());

    public JuegoState(Juego juego){

        apply((JuegoCreado event) ->{
            juego.listaCarros = new ArrayList<>(event.getCantidadCarros());
            juego.limiteCarros = event.getCantidadCarros();
        });

        apply((CarroAgregado carroAgregado)->{
            if(juego.limiteCarros > juego.listaCarros.size() ){
                juego.listaCarros.add(new Carro(carroAgregado.getCarroId(), carroAgregado.getModelo(), carroAgregado.getColor(), carroAgregado.getConductor(), carroAgregado.getDistanciRecoorida() ));
            }else{
                throw new IllegalArgumentException("la cantidad de carros es mayor a la permitida");
            }
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
            for(Carro listaCarro : juego.listaCarros) {
                listaCarro.avanzar();
            }
        });

        apply((PodioVisto podiovisto) ->{
            System.out.println("Primer puesto para: "+ podiovisto.getPodium().getPrimerPuesto());
            System.out.println("Segundo puesto para: "+ podiovisto.getPodium().getSegundoPuesto());
            System.out.println("Tercer puesto para: "+ podiovisto.getPodium().getTercerPuesto());
        });
    }

    public JuegoState(Pista pista){

        apply((CarrilAgregado carrilAgregado) ->{
            pista.listaCarriles.add(new Carril(carrilAgregado.getCarro()));
            pista.longitudPista = carrilAgregado.getLongitudPista();
        });

        apply((RecorridoActualizado recorridoActualizado) ->{
            Integer cantidadCarros = recorridoActualizado.getListaCarros().size();
            if(cantidadCarros <= 0){
                throw new IllegalArgumentException("No hay carros agregados");
            }

            for(int i = 0; i<= cantidadCarros-1; i++){
                pista.listaCarriles.add(new Carril(recorridoActualizado.getListaCarros().get(i)));
            }
        });

        apply((GanadoresIdentificados ganadoresIdentificados) ->{

            pista.longitudPista = ganadoresIdentificados.getLongitudPista();

            pista.listaCarriles.forEach(carril ->{
                if( carril.value().distanciRecoorida().value() >= pista.longitudPista){
                    pista.listaGanadores.add(carril.value());
                }
            });

            if(pista.listaGanadores.size() >= 3){
                pista.carreraFinalizada = true;
            }
        });

        apply((PodioCreado podioCreado) ->{

            if(podioCreado.getGanadoresIdentificados().size() <= 2){
                throw  new IllegalArgumentException("Debe haber tres ganadores en la lista");
            }else{
                String ganador1 = podioCreado.getGanadoresIdentificados().get(0).conductor().nombre();
                String ganador2 = podioCreado.getGanadoresIdentificados().get(1).conductor().nombre();
                String ganador3 = podioCreado.getGanadoresIdentificados().get(2).conductor().nombre();
                pista.podium = new Podium(ganador1, ganador2, ganador3);
            }
        });

    }



}
