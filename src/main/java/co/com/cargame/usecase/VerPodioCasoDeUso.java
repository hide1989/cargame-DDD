package co.com.cargame.usecase;

import co.com.cargame.Juego;
import co.com.cargame.Pista;
import co.com.cargame.agregado.pista.events.PodioCreado;
import co.com.sofka.business.annotation.EventListener;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;

@EventListener(eventType = "com.cargame.podiocreado")
public class VerPodioCasoDeUso extends UseCase<TriggeredEvent<PodioCreado>, ResponseEvents> {


    @Override
    public void executeUseCase(TriggeredEvent<PodioCreado> triggeredEvent) {
        var event = triggeredEvent.getDomainEvent();
        Juego juego = Juego.from(event.getJuegoID(), retrieveEvents());
        Pista pista = Pista.from(event.getPistaId(), retrieveEvents());
        pista.actualizarRecorrido(event.getGanadoresIdentificados(),event.getPistaId());
        pista.asignarPodioPorDistancia(event.getPistaId(),event.getGanadoresIdentificados(), event.getJuegoID());
        juego.verPodio(pista.devolverPodium(), event.getJuegoID());
        emit().onSuccess(new ResponseEvents(juego.getUncommittedChanges()));
    }
}
