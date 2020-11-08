package co.com.cargame.agregado.juego.events;

import co.com.cargame.agregado.juego.vo.CarroId;
import co.com.cargame.agregado.juego.vo.Color;
import co.com.cargame.agregado.juego.vo.DistanciRecoorida;
import co.com.cargame.agregado.juego.vo.Modelo;
import co.com.cargame.agregado.juego.Conductor;
import co.com.sofka.domain.generic.DomainEvent;

public class CarroAgregado extends DomainEvent {
    private final CarroId carroId;
    private final Color color;
    private final Modelo modelo;
    private final Conductor conductor;



    private final DistanciRecoorida distanciRecoorida;

    public CarroAgregado(CarroId carroId, Color color, Modelo modelo, Conductor conductor, DistanciRecoorida distanciRecoorida ){
        super("com.cargame.carroAgregado");
        this.carroId = carroId;
        this.color = color;
        this.modelo = modelo;
        this.conductor = conductor;
        this.distanciRecoorida = distanciRecoorida;
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

    public DistanciRecoorida getDistanciRecoorida() {
        return distanciRecoorida;
    }

}
