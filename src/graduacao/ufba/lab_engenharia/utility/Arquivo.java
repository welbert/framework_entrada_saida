package graduacao.ufba.lab_engenharia.utility;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/*Construtor:
 * 	Arquivo(String nome); Nome se refere ao nome do arquivo
 * 
 * Comandos:
 * 	salvar(String texto);Ele salvara no arquivo a string e pulará a linha no arquivo
 *  
 *  reset(); Ele reiniciará o carregar(), apontando para o inicio do arquivo
 *  
 *  deteletarDado(int indicie);Ele deleta a linha indicada no indice
 *  
 *  modificar(int indice, String palavra); Ele modifica a linha indicada no indice
 *pela String palavra passada por parametro
 *	
 *	carregar();Ele retornará a String presente na linha e pulará a linha do arquivo,
 *caso eu tente ler uma linha null, ele dará um reset() e será lido a primeira linha
 *
 *	NEOF(); Retornará um boolean indincando se o arquivo está apontando para depois
 *da ultima linha
 *
 *	fecha();Ele encerra o arquivo, este comando deve ser SEMPRE executado ao fim do
 *programa
 *
 *	deletarArquivo();Ele cria um novo arquivo em branco com o mesmo nome
 *
 *Arquivo.java by Welbert Serra
 *	Se houver algum erro, enviar para welberts@gmail.com
*/
public class Arquivo  {
	private File arquivo;
	
	private FileWriter fw ;
	private BufferedWriter bw;
	private FileReader fr;
	private BufferedReader br;

	public Arquivo(String nome)throws IOException{
		nome = nome.concat(".txt");
		arquivo = new File(nome);
		fw = new FileWriter(arquivo, true);
		bw = new BufferedWriter(fw);
		fr = new FileReader(arquivo);
		br = new BufferedReader(fr);

		if(arquivo.exists()){
			arquivo.createNewFile();
			
		}fw.close();
		bw.close();
	}

		public void criaArquivo() throws IOException{
			if(!arquivo.exists()){
				arquivo.createNewFile();
			}
		}

	public void salvar(String texto)throws IOException{
		FileWriter fw = new FileWriter(arquivo, true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(texto);
		bw.newLine();
		bw.close();
		fw.close();
	}
	
	public void reset() throws IOException{
		br.close();
		fr.close();
		this.fr = new FileReader(arquivo);
		this.br = new BufferedReader(fr);

	}
	
	public void deletarDado(int indice) throws IOException{
		reset();
		ArrayList<String> aux= new ArrayList<String>(); 
		while(NEOF()){
			aux.add(carregar());
		}
		if(indice<aux.size()){
			deletarArquivo();
			criaArquivo();
				for(int i=0;i<aux.size();i++){
					if(indice == i){
						i++;
					}if(i<aux.size()){	
					salvar(aux.get(i));
					}
				}
		}else{
			System.out.printf("\nA posicao "+indice+" não foi encontrada\n");
		}
		reset();
	}
	
	public void modificar(int indice, String palavra) throws IOException{
		reset();
		ArrayList<String> aux= new ArrayList<String>(); 
		while(NEOF()){
			aux.add(carregar());
		}
		if(indice<aux.size()){
			aux.set(indice, palavra);
			deletarArquivo();
			criaArquivo();
				for(int i=0;i<aux.size();i++){
					salvar(aux.get(i));
				}
		}else{
			System.out.printf("\nA posicao "+indice+" não foi encontrada\n");
		}
		reset();
		
	}
	public String carregar() throws IOException{
		if(NEOF()){
		return br.readLine();
		}else{
			reset();
		return 	br.readLine();
		}		
	}

	public boolean NEOF() throws IOException{
		return br.ready();
	}
	
	public void fecha() throws IOException{
		br.close();
		fr.close();
	}
	
	public void deletarArquivo() throws IOException{
		FileWriter fw = new FileWriter(arquivo, false);
		BufferedWriter bw = new BufferedWriter(fw);
		fw.close();
		bw.close();
	}
}
