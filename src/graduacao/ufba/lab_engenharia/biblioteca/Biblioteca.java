package graduacao.ufba.lab_engenharia.biblioteca;

import graduacao.ufba.lab_engenharia.config.Config;
import graduacao.ufba.lab_engenharia.produto.Produto;
import graduacao.ufba.lab_engenharia.usuario.Usuario;
import graduacao.ufba.lab_engenharia.utility.Arquivo;

import java.util.HashMap;

public class Biblioteca {

	private static Biblioteca instance = new Biblioteca();
	private long cd_user = 0,cd_max_user=0;
	private HashMap<String, Usuario> list_usuarios;
	private HashMap<String,Produto> list_produtos;
	private Arquivo log;
	
	private Biblioteca (){
		list_produtos = new HashMap<>();
		list_usuarios = new HashMap<>();
		try{
			log = new Arquivo("Logs");
		}catch(Exception e){}
	}
	
	public static Biblioteca getInstance(){
		return instance;
	}
	
	public void salvarLogErro(String message){
		if(Config.parametro_log_erro)
			try{
			if(log!=null)
				log.salvar(getDataAtual()+" :: "+message);
			}catch(Exception e){}
	}
	
	public void salvarLogAviso(String message){
		if(Config.parametro_log_aviso)
			try{
			if(log!=null)
				log.salvar(getDataAtual()+" :: "+message);
			}catch(Exception e){}
	}
	
	@SuppressWarnings("deprecation")
	public static String getDataAtual(){
		java.util.Date agora = new java.util.Date();;
		return agora.toGMTString();
	}
	
	public boolean addUsuario(Usuario user){
		long identificador;
		Usuario user_old;
		if(user.getIdentificador() != 0){ //if user want to use your own key management
			cd_max_user = user.getIdentificador();
			identificador = cd_max_user;			
		}else{
			if (cd_max_user != 0 && cd_user == 0)
				cd_user = cd_max_user;
			
			identificador = cd_user++;
		}
		
		user_old = list_usuarios.put(Long.toString(identificador), user); 
		
		if(user_old != null){ // if this is not null, its cuz had a previous values associated with this key
			salvarLogErro("Error 001 - Usuário com chave já existente. Usuário Antigo: "+user_old.toString()+"|| Usuário que foi inserito: "+user.toString());//save in log
			list_usuarios.put(Long.toString(identificador), user_old); // 'rollback'
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
	
}
