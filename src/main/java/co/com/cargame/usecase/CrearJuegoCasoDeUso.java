package co.com.cargame.usecase;

import co.com.cargame.commands.ConfigurarJuego;
import co.com.cargame.Juego;
import co.com.cargame.VO.JuegoID;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;

public class CrearJuegoCasoDeUso extends UseCase<RequestCommand<ConfigurarJuego>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<ConfigurarJuego> configurarJuegoRequestCommand) {
        ConfigurarJuego comando = configurarJuegoRequestCommand.getCommand();
        Juego juego = new Juego(new JuegoID(), comando.getCatidadCarros());

        comando.getCarrosList().forEach((carro) ->{
            juego.agregarCarro(carro.identity(), carro.color(), carro.modelo(), carro.conductor());
        });
        emit().onSuccess(new ResponseEvents(juego.getUncommittedChanges()));
    }
}
