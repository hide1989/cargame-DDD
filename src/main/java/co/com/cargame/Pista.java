package co.com.cargame;

import co.com.cargame.agregado.juego.vo.JuegoID;
import co.com.cargame.agregado.pista.events.CarrilAgregado;
import co.com.cargame.agregado.pista.events.GanadoresIdentificados;
import co.com.cargame.agregado.pista.events.PodioCreado;
import co.com.cargame.agregado.pista.events.RecorridoActualizado;
import co.com.cargame.agregado.pista.vo.PistaId;
import co.com.cargame.agregado.pista.vo.Carril;
import co.com.cargame.agregado.pista.vo.Podium;
import co.com.cargame.agregado.juego.events.JuegoCreado;
import co.com.cargame.agregado.juego.Carro;
import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.ArrayList;
import java.util.List;

public class Pista extends AggregateEvent<PistaId> {

    protected ArrayList<Carril> listaCarriles = new ArrayList<>();
    protected ArrayList<Carro> listaGanadores = new ArrayList<>();
    protected Integer longitudPista;
    protected Boolean carreraFinalizada;
    protected Podium podium;

    public Pista(PistaId entityId, Integer cantidadCarros, Integer longitudPista) {
        super(entityId);
        appendChange(new JuegoCreado(cantidadCarros, longitudPista)).apply();
    }

    public Pista(PistaId pistaId){
        super(pistaId);
        subscribe(new JuegoState(this));
    }

    public static Pista from(PistaId pistaId, List<DomainEvent> events){
        var pista = new Pista(pistaId);
        events.forEach(pista::applyEvent);
        return  pista;
    }

    public void agregarCarril(Integer longitudCarril, Carro carro){
        appendChange(new CarrilAgregado(longitudCarril, carro)).apply();
    }

    public void actualizarRecorrido(ArrayList<Carro> listaCarros, PistaId pistaId){
        appendChange(new RecorridoActualizado(listaCarros, pistaId)).apply();
    }

    public void identificarGanadores(PistaId pistaId, Integer longitudPista){
        appendChange(new GanadoresIdentificados(pistaId, longitudPista)).apply();
    }

    public void asignarPodioPorDistancia(PistaId pistaId,ArrayList<Carro> listaGanadores, JuegoID juegoID){
        appendChange(new PodioCreado(pistaId, listaGanadores, juegoID)).apply();
    }


    public ArrayList<Carro> devolverListaGanadores(){
        return listaGanadores;
    }

    public Podium devolverPodium(){
        return podium;
    }

}
