package aplicacao;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import modelo.Apresentacao;
import modelo.Cidade;
import modelo.Artista;


public class Listar {
	protected ObjectContainer manager;

	public Listar(){
		manager = Util.conectarBanco();
		listar();
		Util.desconectar();
		
		System.out.println("\n\n aviso: feche sempre o plugin OME antes de executar aplicação");
	}

	public void listar(){
		System.out.println("\n---listagem de apresentações:");
		Query q = manager.query();
		q.constrain(Apresentacao.class);
		List<Apresentacao> resultados = q.execute();
		for(Apresentacao a: resultados)
			System.out.println(a);
		
		System.out.println("\n---listagem de cidades:");
		Query q2 = manager.query();
		q2.constrain(Cidade.class);
		List<Cidade> resultados2 = q2.execute();
		for(Cidade c: resultados2)
			System.out.println(c);
		
		System.out.println("\n---listagem de artistas:");
		Query q3 = manager.query();
		q3.constrain(Artista.class);
		List<Artista> resultados3 = q3.execute();
		for(Artista art: resultados3)
			System.out.println(art);
	}
	




	//=================================================
	public static void main(String[] args) {
		new Listar();
	}
}

