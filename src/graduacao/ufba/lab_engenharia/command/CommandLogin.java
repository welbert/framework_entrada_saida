package graduacao.ufba.lab_engenharia.command;

import graduacao.ufba.lab_engenharia.config.TabelaNiveisUsuario;
import graduacao.ufba.lab_engenharia.terminal.Terminal;
import graduacao.ufba.lab_engenharia.usuario.Usuario;

public class CommandLogin extends Command{

	@Override
	public Object[] execute(Object[] dados) {
		// TODO Implementar a lógica de login para a aplicação que irá usar o framework
		return null;
	}

	@Override
	public int getNivelCommand() {
		return TabelaNiveisUsuario.LIVRE;
	}
	
	public boolean setUsuarioAutenticado(){
		return false;
	}


}
