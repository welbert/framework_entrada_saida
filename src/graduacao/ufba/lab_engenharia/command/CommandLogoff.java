package graduacao.ufba.lab_engenharia.command;

import graduacao.ufba.lab_engenharia.config.TabelaNiveisUsuario;

public class CommandLogoff extends Command{

	@Override
	public Object[] execute(Object[] dados) {
		// TODO Implementar a lógica de logoff para a aplicação que irá usar o framework
		return null;
	}

	@Override
	public int getNivelCommand() {
		return TabelaNiveisUsuario.LIVRE;
	}

}
