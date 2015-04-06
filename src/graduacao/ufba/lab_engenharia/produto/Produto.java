package graduacao.ufba.lab_engenharia.produto;

public abstract class Produto {
	
	private long quantidade;
	private String identificador = null;
	private String nome;
	
	public abstract String toString(); //show product's information
	
	public Produto(String identificador,String nome,long quantidade){
		this.quantidade = quantidade;
		this.identificador = identificador;
		this.nome = nome;
	}
	
	public Produto(String nome,long quantidade){
		this.quantidade = quantidade;
		this.nome = nome;
	}

	public long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(long quantidade) {
		this.quantidade = quantidade;
	}
	
	public void addQuantidade(long quantidade) {
		this.quantidade += quantidade;
	}
	
	public boolean removeQuantidade(long quantidade){
		long qtdAux = this.quantidade - quantidade;
		if(qtdAux < 0)
			return false;
		
		this.quantidade = qtdAux;
		return true;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}
