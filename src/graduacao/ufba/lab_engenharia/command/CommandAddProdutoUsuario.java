package graduacao.ufba.lab_engenharia.command;

import graduacao.ufba.lab_engenharia.config.TabelaNiveisUsuario;
import graduacao.ufba.lab_engenharia.estoque.Estoque;
import graduacao.ufba.lab_engenharia.produto.Produto;
import graduacao.ufba.lab_engenharia.usuario.Usuario;

public class CommandAddProdutoUsuario extends Command{

	@Override
	public Object[] execute(Object[] dados) {
		Object[] result = new Object[1]; 
		Produto product = (Produto) dados[0];
		int quantidade = (int) dados[1];
		Usuario user = (Usuario) dados[2];
		result[1] = Estoque.getInstance().addProdutoUsuarioTransacao(product , quantidade, user);
		return result;
	}

	@Override
	public int getNivelCommand() {
		return TabelaNiveisUsuario.ADMINISTRADOR;
	}

}
