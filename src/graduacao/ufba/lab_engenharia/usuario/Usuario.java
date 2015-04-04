package graduacao.ufba.lab_engenharia.usuario;

public abstract class Usuario {
	
		private long identificador = 0;
		private String nome;
		
		public Usuario(long identificador,String nome){
			this.identificador = identificador;
			this.nome = nome;
		}
		
		public Usuario(String nome){
			this.nome = nome;
		}
		
		public abstract String toString();

		public long getIdentificador() {
			return identificador;
		}

		public void setIdentificador(long identificador) {
			this.identificador = identificador;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}
		
		
	
	
}
