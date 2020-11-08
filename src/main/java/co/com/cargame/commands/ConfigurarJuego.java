package co.com.cargame.commands;
import co.com.cargame.agregado.juego.Carro;
import co.com.sofka.domain.generic.Command;

import java.util.List;

public class ConfigurarJuego implements Command {

    private List<Carro> carrosList;
    private Integer catidadCarros;
    private Integer longitudPista;

    public ConfigurarJuego(List<Carro> carrosList, int cantidadCarros, int longitudPista){
        this.carrosList = carrosList;
        this.catidadCarros = cantidadCarros;
        this.longitudPista = longitudPista;
    }

    public ConfigurarJuego(){
    }

    public List<Carro> getCarrosList() {
        return carrosList;
    }

    public void setCarrosList(List<Carro> carrosList) {
        this.carrosList = carrosList;
    }

    public Integer getCatidadCarros() {
        return catidadCarros;
    }

    public void setCatidadCarros(Integer catidadCarros) {
        this.catidadCarros = catidadCarros;
    }

    public Integer getLongitudPista() {
        return longitudPista;
    }

    public void setLongitudPista(Integer longitudPista) {
        this.longitudPista = longitudPista;
    }


}
