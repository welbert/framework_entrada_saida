package graduacao.ufba.lab_engenharia.terminal;



import graduacao.ufba.lab_engenharia.command.CommandAddUser;
import graduacao.ufba.lab_engenharia.command.Command;
import graduacao.ufba.lab_engenharia.config.Config;
import graduacao.ufba.lab_engenharia.estoque.Estoque;
import graduacao.ufba.lab_engenharia.usuario.Usuario;

import java.util.HashMap;



public class Terminal  {

	private static Terminal instance;
	private static Thread ThreadNotificacao;
	private static HashMap<String,Command> commands;
	private Usuario usuario_autenticado = null;
	
	private Terminal (){
		loadCommands();
		initializeThreadNotificacao();
	}
	
	public static Terminal getinstance(){
		if(instance==null)
			instance = new Terminal();
		
		return instance;
	}
	
	public Object executeCommand(String comando,Object input[]){
		if(commands.containsKey(comando)){
			
			return commands.get(comando).preExecute(input);
		}else{
			
			return "Comando inválido!";
			
		}
	}
	
	private static boolean loadCommands(){
		commands = new HashMap<String,Command>();
		
		commands.put("CadastrarUsuario",new CommandAddUser());
		//Example
		//commands.put("Sell",new CommandSell()); //You need to instantiate every command you created, ps: All new command need 'implements' the Command class;
		
		return true;
	}
	
	//Implementacao da thread que varrerá constantemente a lista de noticacao em estoque, respeitando seu tempo
	private static void initializeThreadNotificacao(){ 
		if(Config.parametro_notificacao_ativa){
			ThreadNotificacao = new Thread(Estoque.getInstance().getThreadNotificacao());
			ThreadNotificacao.start();
		}
	}
	
	public boolean setUsuarioAutenticado(Usuario user){
		//Argumento - usuario que está se autenticando
		//Retorno - true = usuario salvo com sucesso
		//			false = já possui instancia de usuario autenticado
		
		if(usuario_autenticado != null)
			return false;
		
		usuario_autenticado = user;
		
		return true;
	}
	
	public boolean removeUsuarioAutenticado(){
		usuario_autenticado = null;
		return true;
	}
	
	public Usuario getUsuarioAutenticado(){
		return usuario_autenticado;
	}
	
	public static void main(String[] args) {
		loadCommands();
		initializeThreadNotificacao();
		
	}

	
	
	
	

}
