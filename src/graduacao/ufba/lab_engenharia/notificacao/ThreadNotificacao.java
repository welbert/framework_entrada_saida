package graduacao.ufba.lab_engenharia.notificacao;

import java.util.ArrayList;

//TODO IMPLEMENTAR ESSA CLASSE
public class ThreadNotificacao implements Runnable{

	private ArrayList<Notificacao> list_notificacao = new ArrayList<Notificacao>();
	
	private synchronized ArrayList<Notificacao> getList_notificacao(){
		
		return list_notificacao;
	}
	
	public  void addNotificacao(Notificacao notificacao){
		
	}
	
	public boolean editNotificacao(Notificacao old_notificacao,Notificacao new_notificacao){
		
		return true;
	}
	
	public boolean removeNotificacao(Notificacao notificacao){
	
		return true;
	}
	
	public int getIndexNotificacao(Notificacao notificacao){
		
		return 1;
	}
	
	public Notificacao getNextNotificacao(){
	
		return null;
	}
	
	@Override
	public void run() {
		
		// TODO Auto-generated method stub
		
	}

}
