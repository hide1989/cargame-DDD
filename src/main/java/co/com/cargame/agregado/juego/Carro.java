package co.com.cargame.agregado.juego;

import co.com.cargame.agregado.juego.vo.CarroId;
import co.com.cargame.agregado.juego.vo.Color;
import co.com.cargame.agregado.juego.vo.DistanciRecoorida;
import co.com.cargame.agregado.juego.vo.Modelo;
import co.com.sofka.domain.generic.Entity;

public class Carro extends Entity<CarroId> {

    protected Color color;
    protected DistanciRecoorida distanciRecoorida;
    protected Modelo modelo;
    protected Conductor conductor;

    public Carro(CarroId placa, Modelo modelo, Color color, Conductor conductor, DistanciRecoorida distanciRecoorida) {
        super(placa);
        this.modelo = modelo;
        this.conductor = conductor;
        this.color = color;
        this.distanciRecoorida = distanciRecoorida;
    }

    public void avanzar(){
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
