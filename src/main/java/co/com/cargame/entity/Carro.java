package co.com.cargame.entity;

import co.com.cargame.VO.CarroId;
import co.com.cargame.VO.Color;
import co.com.cargame.VO.DistanciRecoorida;
import co.com.cargame.VO.Modelo;
import co.com.sofka.domain.generic.Entity;

public class Carro extends Entity<CarroId> {

    protected Color color;
    protected DistanciRecoorida distanciRecoorida;
    protected Modelo modelo;
    protected Conductor conductor;

    public Carro(CarroId placa, Modelo modelo, Color color, Conductor conductor) {
        super(placa);
        this.modelo = modelo;
        this.conductor = conductor;
        this.color = color;
        distanciRecoorida = new DistanciRecoorida(0);
    }

    private void avanzar(){
        int numeroAleatorio = conductor.lanzarDado();
        distanciRecoorida = new DistanciRecoorida(distanciRecoorida.value()+(numeroAleatorio*100));
    }

    public Color color() {
        return color;
    }

    public DistanciRecoorida distanciRecoorida() {
        return distanciRecoorida;
    }

    public Modelo modelo() {
        return modelo;
    }

    public Conductor conductor() {
        return conductor;
    }
}
