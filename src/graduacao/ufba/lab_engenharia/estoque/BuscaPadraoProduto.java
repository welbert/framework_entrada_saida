package graduacao.ufba.lab_engenharia.estoque;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import graduacao.ufba.lab_engenharia.produto.Produto;

public class BuscaPadraoProduto implements BuscaProdutoBehavior{

	//Descri��o - Busca um produto por sua chave indentificadora
			//Argumento - Arg[0] = Chave identificadora do produto
			//Retorno - Object[] = vetor de todos os produtos que atendem a busca 
			
	public ArrayList<Produto> buscarProduto(Object[] Args) {
		HashMap<String,Produto> list_produtos;
		ArrayList<Produto> produtos = new ArrayList<Produto>() ;
		
		//TODO Se for para buscar pela chave indentificador a key do hashmap j� � a pr�pria key logo
		//s� � necess�rio fazer Estoque.getInstance().getListProdutos().get(args[0])
		list_produtos = Estoque.getInstance().getListProdutos();
		
		for(Entry<String, Produto> entry : list_produtos.entrySet()) {
		    Produto produto = entry.getValue();
		    if(produto.getId() ==1){//TODO Ao inv�s de 1 era para ser args[0]?
		    	produtos.add(produto);
		    }
		}
		
		return produtos;
	}

	
}
