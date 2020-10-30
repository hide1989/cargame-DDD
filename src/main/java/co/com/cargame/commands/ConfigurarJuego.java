package co.com.cargame.commands;
import co.com.cargame.entity.Carro;
import co.com.sofka.domain.generic.Command;

import java.util.List;

public class ConfigurarJuego implements Command {

    private List<Carro> carrosList;
    private Integer catidadCarros;

    public ConfigurarJuego(List<Carro> carrosList, int cantidadCarros){
        this.carrosList = carrosList;
        this.catidadCarros = cantidadCarros;
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






}
