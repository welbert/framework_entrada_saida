package graduacao.ufba.lab_engenharia.estoque;

import graduacao.ufba.lab_engenharia.config.Config;
import graduacao.ufba.lab_engenharia.notificacao.Notificacao;
import graduacao.ufba.lab_engenharia.produto.Produto;
import graduacao.ufba.lab_engenharia.transacao.GerenteTransacao;
import graduacao.ufba.lab_engenharia.usuario.Usuario;
import graduacao.ufba.lab_engenharia.utility.Arquivo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Estoque {

	private static Estoque instance;
	private long cd_user = 1;
	private HashMap<String, Usuario> list_usuarios;
	private HashMap<String,Produto> list_produtos;
	private ArrayList<Notificacao> list_notificacao;
	private Arquivo log;
	private BuscaProdutoBehavior algoritmo_busca_produto;
	private GerenteTransacao gerente_transacao;
	
	private Estoque (){
		list_produtos = new HashMap<>();
		list_usuarios = new HashMap<>();
		try{
			log = new Arquivo("Logs");
		}catch(Exception e){}
		
		try {
			this.algoritmo_busca_produto = (BuscaProdutoBehavior) Class.forName(Config.parametro_search_product_class).newInstance();
		} catch (Exception e) {
			Estoque.getInstance().salvarLogErro("Error ao instanciar o algoritmo da busca - "+ e.getMessage());
		}
		
	}
	
	public static Estoque getInstance(){
		if(instance == null)
			instance = new Estoque();
		return instance;
	}
	
	public void salvarLogErro(String message){
		if(Config.parametro_log_erro)
			try{
			if(log!=null)
				log.salvar(getDataAtualString()+" :: "+message);
			}catch(Exception e){}
	}
	
	public void salvarLogAviso(String message){
		if(Config.parametro_log_aviso)
			try{
			if(log!=null)
				log.salvar(getDataAtualString()+" :: "+message);
			}catch(Exception e){}
	}
	
	@SuppressWarnings("deprecation")
	public static String getDataAtualString(){
		java.util.Date agora = new java.util.Date();
		return agora.toGMTString();
	}
	
	public static Date getDataAtual(){
		Date agora = new Date();
		return agora;
	}
	
	public boolean addUsuario(Usuario user){
		long identificador;
		Usuario user_old;
		if(user.getIdentificador() == null){ //if user want to use your own key management
			identificador = cd_user++;
			user.setIdentificador(Long.toString(identificador));
		}
		
		user_old = list_usuarios.put(user.getIdentificador(), user); 
		
		if(user_old != null){ // if this is not null, it's because had a previous values associated with this key
			salvarLogErro("Error 001 - Usu�rio com chave j� existente. Usu�rio Antigo: "+user_old.toString()+"|| Usu�rio que foi inserito: "+user.toString());//save in log
			list_usuarios.put(user_old.getIdentificador(), user_old); // 'rollback'
			return false;
		}
		
		return true;
	}
	
	public boolean addProduto(Produto product){
		String identificador;
		Produto product_old;
		if(product.getIdentificador() != null){ //if user want to use your own key management
			identificador = product.getIdentificador();			
		}else{						
			identificador = product.getNome();
		}
		
		product_old = list_produtos.get(identificador);
		
		if(product_old == null)
			list_produtos.put(identificador, product);
		else{
			salvarLogAviso("Aviso 001: O produto "+product.toString()+" possuia a mesma identifica��o que "+product_old.toString()+" ,logo foi adicionado somente a quantidade.");
			product_old.addQuantidade(product.getQuantidade());			
		}
		
		return true;
	}
	
	public boolean editProduct(Produto old_product,Produto new_product){
		//TODO fazer a remocao do antigo e adicionar o novo
		return true;
	}
	
	public boolean removeProduto(Produto product, long quantidade){
		if(!product.removeQuantidade(quantidade))
			return false;
		
		return true;
	}
	
	public boolean alterarQuantidadeProduto(Produto product,int quantidade){
		//TODO Fazer a altera��o da quantidade do produto
		return true;
	}
	
	private synchronized ArrayList<Notificacao> getList_notificao(){
		return list_notificacao;
	}
	
	public  boolean addNotificacao(Notificacao notificacao){
		return getList_notificao().add(notificacao);
	}
	
	public boolean editNotificacao(Notificacao old_notificacao,Notificacao new_notificacao){
		//TODO fazer a remocao do antigo e adicionar o novo
		return true;
	}
	
	public boolean removeNotificacao(Notificacao notificacao){
		return getList_notificao().remove(notificacao);
	}
	
	public Notificacao getNextNotificacao(){
		//TODO fazer a logica para pegar a proxima notificacao na lista, criar uma variavel pra armazenar o index da ultima notificao usada
		return null;
	}
	
	public Produto[] buscaProduto(Object[] Args){
		return algoritmo_busca_produto.buscarProduto(Args);
	}
	
	public boolean addProdutoUsuarioTransacao(Produto product,int quantidade,Usuario user){
		if(!alterarQuantidadeProduto(product, quantidade))
			return false;
		
		Produto new_product = product.getCopiaProduto(); //necess�rio para n�o se alterar o original
		new_product.setQuantidade(quantidade);
		return gerente_transacao.addProdutoTransacao(user, new_product);
	}
	
	public boolean iniciarTransacao(Usuario user){
		
		return gerente_transacao.iniciarTransacao(user);
	}
	
	public boolean fecharTransacao(Usuario user, Object[] Args){
		return gerente_transacao.fecharTransacao(user, Args);
	}
	
	public boolean cancelarTransacao(Usuario user){
		return gerente_transacao.cancelarTransacao(user);
	}
	
	public String emitirNotaFiscal(Usuario user){
		return gerente_transacao.emitirNotaFiscal(user);
	}
	
	public String emitirHistorcio(Usuario user){
		return gerente_transacao.emitirHistorio(user);
	}
}
