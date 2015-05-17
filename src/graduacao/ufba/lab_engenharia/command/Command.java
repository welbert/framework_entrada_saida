package graduacao.ufba.lab_engenharia.command;

import graduacao.ufba.lab_engenharia.config.TabelaNiveisUsuario;
import graduacao.ufba.lab_engenharia.terminal.Terminal;
import graduacao.ufba.lab_engenharia.usuario.Usuario;

public abstract class Command {

	public abstract Object[] execute(Object dados[]);
	
	public abstract int getNivelCommand();
	
	public Usuario getUsuarioAutenticado(){
		return Terminal.getinstance().getUsuarioAutenticado();
	}
	
	public boolean verificaAcesso(){
		//Descrição - Verifica o acesso do usuario ao executar o comando
		//Argumento - Nenhum
		//Retorno - true = usuario com permissão
		//			false = usuario sem permissão
		if(getUsuarioAutenticado() == null)
			return false;
		
		if(getNivelCommand() <= getUsuarioAutenticado().getNivel_acesso() || getNivelCommand() == TabelaNiveisUsuario.LIVRE)
			return true;
		
		return false;
	}
	
	public Object[] preExecute(Object dados[]){
		//Descrição - Verifica o acesso do usuario ao executar o comando
		//Argumento - dados[] = input que pode ser utilizado nos comandos
		//Retorno - Object[] = vetor de resultado do comando
		if(verificaAcesso())
			return execute(dados);
		
		return null;
	}
	
}
