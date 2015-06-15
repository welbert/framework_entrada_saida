package graduacao.ufba.lab_engenharia.command;

import graduacao.ufba.lab_engenharia.config.TabelaNiveisUsuario;

public class CommandLogoff extends Command{

	@Override
	public Object[] execute(Object[] dados) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNivelCommand() {
		return TabelaNiveisUsuario.LIVRE;
	}

}
