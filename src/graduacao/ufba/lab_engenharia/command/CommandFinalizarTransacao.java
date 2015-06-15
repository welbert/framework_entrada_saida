package graduacao.ufba.lab_engenharia.command;

import graduacao.ufba.lab_engenharia.config.TabelaNiveisUsuario;
import graduacao.ufba.lab_engenharia.estoque.Estoque;

public class CommandFinalizarTransacao extends Command{

	@Override
	public Object[] execute(Object[] dados) {
			Object[] result = new Object[1]; 		
			result[1] = Estoque.getInstance().fecharTransacao(dados);
		return result;
	}

	@Override
	public int getNivelCommand() {
		return TabelaNiveisUsuario.USUARIO;
	}

}
