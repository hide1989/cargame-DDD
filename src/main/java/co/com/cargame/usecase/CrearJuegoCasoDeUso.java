package co.com.cargame.usecase;

import co.com.cargame.Pista;
import co.com.cargame.agregado.pista.vo.PistaId;
import co.com.cargame.commands.ConfigurarJuego;
import co.com.cargame.Juego;
import co.com.cargame.agregado.juego.vo.JuegoID;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;

public class CrearJuegoCasoDeUso extends UseCase<RequestCommand<ConfigurarJuego>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<ConfigurarJuego> configurarJuegoRequestCommand) {
        ConfigurarJuego comando = configurarJuegoRequestCommand.getCommand();
        Juego juego = new Juego(new JuegoID(), comando.getCatidadCarros(), comando.getLongitudPista());
        Pista pista = new Pista(new PistaId(), comando.getCatidadCarros(), comando.getLongitudPista());
        Integer cantidadCarriles =  comando.getCarrosList().size();

        comando.getCarrosList().forEach((carro) ->{
            juego.agregarCarro(carro.identity(), carro.color(), carro.modelo(), carro.conductor(), carro.distanciRecoorida());
        });

        for(int i = 0; i <= cantidadCarriles-1; i++){
            pista.agregarCarril(comando.getLongitudPista(), comando.getCarrosList().get(i));
        }

        emit().onSuccess(new ResponseEvents(juego.getUncommittedChanges()));
    }

}
