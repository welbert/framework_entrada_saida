package graduacao.ufba.lab_engenharia.command;

import graduacao.ufba.lab_engenharia.config.TabelaNiveisUsuario;
import graduacao.ufba.lab_engenharia.estoque.Estoque;
import graduacao.ufba.lab_engenharia.usuario.Usuario;

public class CommandIniciarTransacao extends Command{

	@Override
	public Object[] execute(Object[] dados) {
			Object[] result = new Object[1]; 
			Usuario user = (Usuario) dados[0];
			result[1] = Estoque.getInstance().iniciarTransacao(user);
		return result;
	}

	@Override
	public int getNivelCommand() {
		return TabelaNiveisUsuario.USUARIO;
	}

}
