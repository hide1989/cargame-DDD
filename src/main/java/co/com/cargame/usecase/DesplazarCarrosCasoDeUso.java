package co.com.cargame.usecase;

import co.com.cargame.Juego;
import co.com.cargame.Pista;
import co.com.cargame.events.CarreraIniciada;
import co.com.sofka.business.annotation.EventListener;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;

import java.util.logging.Level;
import java.util.logging.Logger;

@EventListener(eventType = "com.cargame.carrerainiciada")
public class DesplazarCarrosCasoDeUso extends UseCase<TriggeredEvent<CarreraIniciada>, ResponseEvents> {

    private static final Logger logger = Logger.getLogger(DesplazarCarrosCasoDeUso.class.getName());

    @Override
    public void executeUseCase(TriggeredEvent<CarreraIniciada> triggeredEvent){
        var event = triggeredEvent.getDomainEvent();
        Juego juego = Juego.from(event.getJuegoID(), retrieveEvents());

        if(event.isCarreraIniciada()){
            juego.moverCarros(event.getListaCarros(),event.getJuegoID());
        }else{
            logger.log(Level.INFO, "No se ha iniciado la Carrera");
        }

        emit().onSuccess(new ResponseEvents(juego.getUncommittedChanges()));
    }

}
