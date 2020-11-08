package co.com.cargame.usecase;

import co.com.cargame.Pista;
import co.com.cargame.agregado.juego.events.CarrosDesplazados;
import co.com.sofka.business.annotation.EventListener;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;

import java.util.logging.Level;
import java.util.logging.Logger;

@EventListener(eventType = "com.cargame.carrosdesplazados")
public class AdministrarCarreraCasoDeUso extends UseCase<TriggeredEvent<CarrosDesplazados>, ResponseEvents> {

    private static final Logger logger = Logger.getLogger(DesplazarCarrosCasoDeUso.class.getName());

    @Override
    public void executeUseCase(TriggeredEvent<CarrosDesplazados> triggeredEvent) {

        var event = triggeredEvent.getDomainEvent();
        Pista pista = Pista.from(event.getPistaId(), retrieveEvents());

        if(event.getListaCarros().size() > 2){
            pista.actualizarRecorrido(event.getListaCarros(), event.getPistaId());
            pista.identificarGanadores(event.getPistaId(), event.getLongitudPista());
            if(pista.devolverListaGanadores().size() > 2){
                pista.asignarPodioPorDistancia(event.getPistaId(), pista.devolverListaGanadores(), event.getJuegoID());
            }
        }else{
            logger.log(Level.INFO, "la cantidad de carros debe ser igual o mayor a 3");
        }

        emit().onSuccess(new ResponseEvents(pista.getUncommittedChanges()));
    }
}
