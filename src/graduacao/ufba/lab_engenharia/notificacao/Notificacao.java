package graduacao.ufba.lab_engenharia.notificacao;

import graduacao.ufba.lab_engenharia.config.Config;
import graduacao.ufba.lab_engenharia.estoque.Estoque;

import java.util.Date;

public class Notificacao {
	private String text;
	private int quantidade;
	private int tempo;
	private String tipo;
	private NotificaBehavior notifica;
	private Date ultima_notificacao;
	
	
	
	public Notificacao(String text, int quantidade, int tempo, String tipo) {
		this.text = text;
		this.quantidade = quantidade;
		this.tempo = tempo;
		this.tipo = tipo;
		try {
			this.notifica = (NotificaBehavior) Class.forName(Config.parametro_notificabehavior_class).newInstance();
		} catch (Exception e) {
			Estoque.getInstance().salvarLogErro("Error ao instanciar Notificação - "+ e.getMessage());
		}
	}
	
	public void notificar(){
		if(notifica!=null){
			notifica.notificar(this);
			ultima_notificacao = Estoque.getDataAtual();
		}
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public int getTempo() {
		return tempo;
	}
	public void setTempo(int tempo) {
		this.tempo = tempo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Date getUltima_notificacao() {
		return ultima_notificacao;
	}
	public void setUltima_notificacao(Date ultima_notificacao) {
		this.ultima_notificacao = ultima_notificacao;
	}
	
	
}
