package graduacao.ufba.lab_engenharia.transacao;

import graduacao.ufba.lab_engenharia.usuario.Usuario;

import java.util.ArrayList;
import java.util.HashMap;

public class HistoricoTransacoes {

	private HashMap<String, ArrayList<Transacao>> list_transacao;
	
	public HistoricoTransacoes(){
		list_transacao = new HashMap<>();
	}
	
	public boolean addTransacao(Transacao transaction,Usuario user){
		if(list_transacao.containsKey(user.getIdentificador())){
			list_transacao.get(user.getIdentificador()).add(transaction);
		}else{
			ArrayList<Transacao> new_list = new ArrayList<Transacao>();
			new_list.add(transaction);
			list_transacao.put(user.getIdentificador(), new_list);
		}
		return true;
	}
	
	public String emitirHistoricoUsuario(Usuario user){
		ArrayList<Transacao> user_transacao = list_transacao.get(user.getIdentificador());
		String result="";	
		if(user_transacao == null)
			return null;
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("------------------------EXTRATO DE ");
		stringBuilder.append(user.getNome());
		stringBuilder.append("------------------------");
		stringBuilder.append("\n");
		
		int size = user_transacao.size(),i;
		for(i = 0; i<size;i++){
			stringBuilder.append(user_transacao.get(i).emitirNotaFiscal());
			stringBuilder.append("\n");
		}
		stringBuilder.append("-----------------------------------------------------------------");
		
		result = stringBuilder.toString();
		return result;

	}
}
