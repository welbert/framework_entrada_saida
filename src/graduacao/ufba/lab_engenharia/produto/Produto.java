package graduacao.ufba.lab_engenharia.produto;


public abstract class Produto {
	
	private long quantidade;
	private long id;
	private String nome;
	protected String identificador;
	
	public abstract String toString(); //show product's information
	
	public abstract String toStringNotaFiscal(); //show product's information
	
	public abstract Produto getCopiaProduto();
	//Exemplo de copia:
	//Produto copia = new Produto(this.getnome,this.....);
	//
	
	public Produto(long id,String nome,long quantidade){
		this.quantidade = quantidade;
		this.id = id;
		this.nome = nome;
	}
	
	public Produto(String nome,long quantidade){
		this.quantidade = quantidade;
		this.nome = nome;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public abstract String getIdentificador();

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
