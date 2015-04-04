package graduacao.ufba.lab_engenharia.terminal;



import graduacao.ufba.lab_engenharia.command.Command;

import java.util.HashMap;



public class Terminal {

	private static Terminal instance = new Terminal();
	private static HashMap<String,Command> commands;
	
	private Terminal (){
		loadCommands();
	}
	
	public static Terminal getinstance(){
		return instance;
	}
	
	public Object executeCommand(String comando,Object input[]){
		if(commands.containsKey(comando)){
			
			return commands.get(comando).execute(input);
		}else{
			
			return "Comando inválido!";
			
		}
	}
	
	private static boolean loadCommands(){
		commands = new HashMap<String,Command>();
		
		//Example
		//commands.put("Sell",new CommandSell()); //You need to instantiate every command you created, ps: All new command need 'implements' the Command class;
		
		return true;
	}
	
	public static void main(String[] args) {
		loadCommands();

	}
	
	
	

}
