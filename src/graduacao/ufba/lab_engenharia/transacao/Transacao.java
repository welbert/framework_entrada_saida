package graduacao.ufba.lab_engenharia.transacao;

import graduacao.ufba.lab_engenharia.estoque.Estoque;
import graduacao.ufba.lab_engenharia.produto.Produto;

import java.util.ArrayList;
import java.util.Date;

public class Transacao {
	private ArrayList<Produto> list_produto = new ArrayList<Produto>();
	private Date data_transacao;
	
	public Transacao(){
		data_transacao = Estoque.getDataAtual();
	}
	
	public boolean addProduto(Produto product){		
		return list_produto.add(product);
	}
	
	public boolean removeProduto(Produto product){		
		return list_produto.remove(product);
	}
	
	@SuppressWarnings("deprecation")
	public String emitirNotaFiscal(){
		String result=null;
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("------------Nota Fiscal---------------\n");		
		stringBuilder.append("Emitida em ");
		stringBuilder.append(data_transacao.toGMTString());
		int size = list_produto.size();
		for (int i =0;i<size;i++){
			stringBuilder.append(list_produto.get(i).toStringNotaFiscal());
			stringBuilder.append("\n");
		}
		stringBuilder.append("--------------------------------------");
		result = stringBuilder.toString();
		return result;
	}

	public Produto[] getList_produto() {
		return (Produto[]) list_produto.toArray();
	}

	public Date getData_transacao() {
		return data_transacao;
	}

	public void setData_transacao(Date data_transacao) {
		this.data_transacao = data_transacao;
	}
	
	
}
