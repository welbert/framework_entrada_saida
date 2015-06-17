package graduacao.ufba.lab_engenharia.notificacao;

public interface NotificaBehavior {

	public void notificar(Notificacao notificacao);
	public void atualizaTempo(Notificacao notificacao);//TODO: Atualizar D.C.
	//Calcula tempo atual + offset, que pode ser por mes, data, hora,
	//Exemplo:tempo.setHours(getOffset());
}
