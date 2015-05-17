package graduacao.ufba.lab_engenharia.command;

import graduacao.ufba.lab_engenharia.config.TabelaNiveisUsuario;
import graduacao.ufba.lab_engenharia.estoque.Estoque;
import graduacao.ufba.lab_engenharia.usuario.Usuario;

public class CommandAddUser extends Command{

	@Override
	public Object[] execute(Object[] dados) {
			Object[] result = new Object[1]; 
			result[1] = Estoque.getInstance().addUsuario((Usuario) dados[1]);
		return result;
	}

	@Override
	public int getNivelCommand() {		
		return TabelaNiveisUsuario.ADMINISTRADOR;
	}

}
