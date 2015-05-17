package graduacao.ufba.lab_engenharia.config;

public final class Config {

	public static final boolean parametro_log_erro = true;
	public static final boolean parametro_log_aviso = true;
	public static final String parametro_search_product_class = "BuscaPadraoProduto"; //Classe que será instanciada na busca do produto (Object xyz = Class.forName(className).newInstance();)
	public static final String parametro_notificabehavior_class = "NotificaBehavior";
	public static final String parametro_regra_fechamento_class = "";
	public static final boolean parametro_notificacao_ativa = true;
}
