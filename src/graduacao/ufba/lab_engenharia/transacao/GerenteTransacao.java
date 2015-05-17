package graduacao.ufba.lab_engenharia.transacao;

import graduacao.ufba.lab_engenharia.config.Config;
import graduacao.ufba.lab_engenharia.estoque.Estoque;
import graduacao.ufba.lab_engenharia.produto.Produto;
import graduacao.ufba.lab_engenharia.regras.RegraFechamentoTransacao;
import graduacao.ufba.lab_engenharia.usuario.Usuario;

import java.util.HashMap;

public class GerenteTransacao {

	private HashMap<String, Transacao> list_transacao;
	private RegraFechamentoTransacao regra_fechamento;
	private HistoricoTransacoes historico_transacao;
	
	public GerenteTransacao(){
		list_transacao = new HashMap<String,Transacao>();
		historico_transacao = new HistoricoTransacoes();
		try{
		regra_fechamento = (RegraFechamentoTransacao) Class.forName(Config.parametro_regra_fechamento_class).newInstance();
		}catch(Exception e){
			Estoque.getInstance().salvarLogErro("Error ao instanciar regra de fechamento - "+ e.getMessage());
		}		
	}
	
	public boolean iniciarTransacao(Usuario user){
		
		//TODO Antes de adicionar a transacao, verificar se outro ja existia, se sim return false, senao adicione e return true. OBS: chave para hashmap = user.getIdentificador()
		return true;
	}
	
	public boolean fecharTransacao(Usuario user,Object[] Args){
		if(regra_fechamento.isValidRegra(Args)){
			addHistorico(user);
			list_transacao.remove(user.getIdentificador());
			return true;
		}
		return false;
	}
	
	public boolean cancelarTransacao(Usuario user){
		//TODO Antes de remover a transação, colocar de volta os produtos
		return true;
	}
	
	public boolean addProdutoTransacao(Usuario user,Produto product){
		Transacao transaction = list_transacao.get(user.getIdentificador());
		if(transaction == null)
			return false;
		
		
		return transaction.addProduto(product); //TODO testar se isso salva como referencia ou se apenas na variavel local transaction
	}
	
	public boolean removeProdutoTransacao(Usuario user,Produto product){
		Transacao transaction = list_transacao.get(user.getIdentificador());
		if(transaction == null)
			return false;
		
		return transaction.removeProduto(product);
	}
	
	public String emitirNotaFiscal(Usuario user){
		return list_transacao.get(user.getIdentificador()).emitirNotaFiscal();
	}
	
	public boolean addHistorico(Usuario user){
		return historico_transacao.addTransacao(list_transacao.get(user.getIdentificador()), user);
	}
	
	public String emitirHistorio(Usuario user){
		return historico_transacao.emitirHistoricoUsuario(user);
	}
	
}
