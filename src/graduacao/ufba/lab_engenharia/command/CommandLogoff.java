package graduacao.ufba.lab_engenharia.command;

import graduacao.ufba.lab_engenharia.config.TabelaNiveisUsuario;

public class CommandLogoff extends Command{

	@Override
	public Object[] execute(Object[] dados) {
		// TODO Implementar a l�gica de logoff para a aplica��o que ir� usar o framework
		return null;
	}

	@Override
	public int getNivelCommand() {
		return TabelaNiveisUsuario.LIVRE;
	}

}
