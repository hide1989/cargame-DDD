package co.com.cargame.usecase;

import co.com.cargame.Juego;
import co.com.cargame.Pista;
import co.com.cargame.commands.IniciarCarrera;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;

import java.util.logging.Logger;


public class IniciarCarreraCasoDeUso extends UseCase<RequestCommand<IniciarCarrera>, ResponseEvents> {
    private static final Logger logger = Logger.getLogger(IniciarCarreraCasoDeUso.class.getName());
    @Override
    public void executeUseCase(RequestCommand<IniciarCarrera> iniciarCarreraRequestCommand){
        IniciarCarrera comando = iniciarCarreraRequestCommand.getCommand();
        Juego juego = Juego.from(comando.getJuegoID(), retrieveEvents());
        juego.iniciarCarrera(comando.getJuegoID(), comando.getListaCarros());
        emit().onSuccess(new ResponseEvents((juego.getUncommittedChanges())));
    }

}
