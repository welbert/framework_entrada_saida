package graduacao.ufba.lab_engenharia.usuario;

public abstract class Usuario {
	
		private long id = 0;
		protected String identificador;
		private String nome;
		private String senha;
		private int nivel_acesso;
		
		public Usuario(long id,String nome){
			this.id = id;
			this.nome = nome;
		}
		
		public Usuario(String nome){
			this.nome = nome;
		}
		
		public abstract String toString();

		public abstract String getIdentificador();
		
		public void setIdentificador(String identificador){
			this.identificador = identificador;
		}
		
		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getSenha() {
			return senha;
		}

		public void setSenha(String senha) {
			this.senha = senha;
		}

		public int getNivel_acesso() {
			return nivel_acesso;
		}

		public void setNivel_acesso(int nivel_acesso) {
			this.nivel_acesso = nivel_acesso;
		}
		
		
	
	
}
