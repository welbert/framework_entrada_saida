package graduacao.ufba.lab_engenharia.notificacao;

import graduacao.ufba.lab_engenharia.config.Config;
import graduacao.ufba.lab_engenharia.estoque.Estoque;

import java.util.Calendar;
import java.util.Date;

public class Notificacao implements NotificaBehavior{
	private String text;
	private int quantidade;
	private Date tempo;//TODO: Atualizar diagrama de classes
	private int offset;//TODO: Atualizar diagrama de classes
	private String tipo;
	private NotificaBehavior notifica;
	private Date ultima_notificacao;
	
	
	
	@SuppressWarnings("deprecation")
	public Notificacao(String text, int quantidade,Date tempo, int offset, String tipo) {
		this.text = text;
		this.quantidade = quantidade;
		this.offset = offset;
		this.tempo = tempo;
		this.tipo = tipo;
		try {
			this.notifica = (NotificaBehavior) Class.forName(Config.parametro_notificabehavior_class).newInstance();
		} catch (Exception e) {
			Estoque.getInstance().salvarLogErro("Error ao instanciar Notificação - "+ e.getMessage());
		}
	}
	
	
	public void notificar(){//Isso é um default?TODO: N vejo real necessidade
		if(notifica!=null){
			notifica.notificar(this);
			ultima_notificacao = Estoque.getDataAtual();
		}
	}
	
	//TODO: Atualizar diagrama de classe://PONTO FLEXIVEL
	public void notificar(Notificacao notifica){
	//Implementado pelo usuário
	}
	
	
	//TODO: Atualizar diagrama de classe//PONTO FLEXIVEL
	public void atualizaTempo(){
		//Calcula tempo atual + offset, que pode ser por mes, data, hora, etc...
		//Cabe ao usuario determinar offset
		//Exemplo:
		/*
		 * tempo.setHours(getOffset());
		 */
		
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
	public Date getTempo() {
		return tempo ;
	}
	public int getOffset() {
		return offset;
	}
	//TODO: Atualizar diagrama de classe
	public void setTempo(Date tempo) {
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
