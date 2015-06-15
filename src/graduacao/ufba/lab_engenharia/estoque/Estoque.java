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
import java.util.Map.Entry;

public class Estoque implements Runnable{

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
			salvarLogErro("Error 001 - Usuário com chave já existente. Usuário Antigo: "+user_old.toString()+"|| Usuário que foi inserito: "+user.toString());//save in log
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
			salvarLogAviso("Aviso 001: O produto "+product.toString()+" possuia a mesma identificação que "+product_old.toString()+" ,logo foi adicionado somente a quantidade.");
			product_old.addQuantidade(product.getQuantidade());			
		}
		
		return true;
	}
	//implementado por Ive
	//TODO: Incluir no Diagrama de Classes
	public HashMap<String,Produto> getListProdutos(){
		return list_produtos;
	}
	
	//implementado por Ive
	//TODO: Incluir no Diagrama de Classes
	public String returnKeyObject(Produto product){
		for(Entry<String, Produto> entry : list_produtos.entrySet()) {
		    Produto produto = entry.getValue();
		    if(produto.equals(product)){
		    	return entry.getKey();
		    }
		}
		return null;
	}
	
	//implementado por Ive
	public boolean editProduct(Produto old_product,Produto new_product){
		return	 list_produtos.replace(returnKeyObject(old_product), old_product, new_product);
	}
	
	
	public boolean removeProduto(Produto product, long quantidade){
		if(!product.removeQuantidade(quantidade))
			return false;
		
		return true;
	}
	
	//implementado por Ive
	public boolean alterarQuantidadeProduto(Produto product,int quantidade){
		Produto produto_novo = product;
		produto_novo.setQuantidade(quantidade);
		editProduct(product,produto_novo);
		return true;
	}
	
	private synchronized ArrayList<Notificacao> getList_notificacao(){
		return list_notificacao;
	}
	
	public  void addNotificacao(Notificacao notificacao){
		int tempo = notificacao.getTempo();
		for(Notificacao notifi: getList_notificacao() ){
			if(tempo < notifi.getTempo()){
				getList_notificacao().add(getList_notificacao().indexOf(notifi), notificacao);
				break;
			}
		}
	}
	
	//implementado por Ive
	public boolean editNotificacao(Notificacao old_notificacao,Notificacao new_notificacao){
		int index = getList_notificacao().indexOf(old_notificacao);
		getList_notificacao().add(index, new_notificacao);
		if(index > -1){
			return true;
		}
		else{
			return false;
		}
	}

	public boolean removeNotificacao(Notificacao notificacao){
		return getList_notificacao().remove(notificacao);
	}
	
	//implementado por Ive
	//TODO: Incluir no Diagrama de Classes
	public int getIndexNotificacao(Notificacao notificacao){
		return getList_notificacao().indexOf(notificacao);
	}
	
	//Obter a notificação mais atual, atualizar seu tempo e reordena-la na fila
	public Notificacao getNextNotificacao(){
		Notificacao notifi = getList_notificacao().get(0);
		notifi.setTempo();//Atualiza tempo
		//Realoca a primeira da fila
		getList_notificacao().remove(0);
		addNotificacao(notifi);
		//Notificar:
		notifi.notificar();
		return notifi;
	}
	
	//Ive: TODO: Mudar no Diagrama de Classe
	public ArrayList<Produto> buscaProduto(Object[] Args){
		return algoritmo_busca_produto.buscarProduto(Args);
	}
	
	public boolean addProdutoUsuarioTransacao(Produto product,int quantidade,Usuario user){
		if(!alterarQuantidadeProduto(product, quantidade))
			return false;
		
		Produto new_product = product.getCopiaProduto(); //necessário para não se alterar o original
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

	@Override
	public void run() {
		// TODO Auto-generated method stub
		getNextNotificacao();
		try {
			Thread.sleep(getList_notificacao().get(0).getTempo()*1000);//Colocar a thread para dormir em milisegundos
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
