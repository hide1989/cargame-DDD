package co.com.cargame.usecase;

import co.com.cargame.events.CarrosDesplazados;
import co.com.sofka.business.annotation.EventListener;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;

@EventListener(eventType = "com.cargame.carrosdesplazados")
public class ActualizarRecorridosCasoDeUso extends UseCase<TriggeredEvent<CarrosDesplazados>, ResponseEvents> {

    @Override
    public void executeUseCase(TriggeredEvent<CarrosDesplazados> triggeredEvent) {

        var event = triggeredEvent.getDomainEvent();
    }
}
