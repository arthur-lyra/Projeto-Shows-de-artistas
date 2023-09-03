package aplicacao;

import com.db4o.ObjectContainer;

import modelo.Apresentacao;
import modelo.Cidade;
import modelo.Artista;


public class Cadastrar {
	protected ObjectContainer manager;

	public Cadastrar(){
		manager = Util.conectarBanco();
		cadastrar();
		Util.desconectar();
		
		System.out.println("fim da aplicacao");
	}


	public void cadastrar(){
		System.out.println("cadastrando...");
		Apresentacao apresentacao ;
		
		Artista artista1 = new Artista("Fausto Ayres");
		Artista artista2 = new Artista("Arthur Lyra");
		Artista artista3 = new Artista("Brian Rafael");
		
		Cidade cidade1 = new Cidade("João Pessoa");
		Cidade cidade2 = new Cidade("Sapé");
		Cidade cidade3 = new Cidade("Campina Grande");

		apresentacao = new Apresentacao(1, "29/03/2024", artista1, cidade1, 200);
		artista1.adicionar(apresentacao);
		manager.store(apresentacao);
		manager.commit();
		
		apresentacao = new Apresentacao(2, "17/10/2023", artista2, cidade2, 85);
		artista2.adicionar(apresentacao);
		manager.store(apresentacao);
		manager.commit();

		apresentacao = new Apresentacao(3, "07/09/2023", artista3, cidade3, 125);
		artista3.adicionar(apresentacao);
		manager.store(apresentacao);
		manager.commit();
		
		apresentacao = new Apresentacao(4, "15/09/2023", artista3, cidade1, 125);
		artista3.adicionar(apresentacao);
		manager.store(apresentacao);
		manager.commit();
		
		System.out.println("cadastrou.");
	}	


	//=================================================
	public static void main(String[] args) {
		new Cadastrar();
	}
}


