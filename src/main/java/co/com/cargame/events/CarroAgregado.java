package co.com.cargame.events;

import co.com.cargame.VO.CarroId;
import co.com.cargame.VO.Color;
import co.com.cargame.VO.Modelo;
import co.com.cargame.entity.Conductor;
import co.com.sofka.domain.generic.DomainEvent;

public class CarroAgregado extends DomainEvent {
    private final CarroId carroId;
    private final Color color;
    private final Modelo modelo;
    private final Conductor conductor;

    public CarroAgregado(CarroId carroId, Color color, Modelo modelo, Conductor conductor ){
        super("com.cargame.carroAgregado");
        this.carroId = carroId;
        this.color = color;
        this.modelo = modelo;
        this.conductor = conductor;
    }

    public CarroId getCarroId() {
        return carroId;
    }

    public Color getColor() {
        return color;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public Conductor getConductor() {
        return conductor;
    }

}
