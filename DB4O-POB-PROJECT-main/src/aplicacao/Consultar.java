package aplicacao;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import modelo.Apresentacao;
import modelo.Artista;


public class Consultar {
	protected ObjectContainer manager;

	public Consultar(){
		manager = Util.conectarBanco();
		consultar();
		Util.desconectar();
		
		System.out.println("\n\n aviso: feche sempre o plugin OME antes de executar aplicação");
	}

	public void consultar(){
		System.out.println("\n---listar Apresentações na data 17/10/2023");
		Query q = manager.query();
		q.constrain(Apresentacao.class);
		q.descend("data").constrain("17/09/2023");
		List<Apresentacao> resultados = q.execute();
			if(resultados.size()>0) {
				for(Apresentacao a: resultados) { 
					System.out.println(a);
				}
			}else{ 
				System.out.println("Nenhuma apresentação na Data atual.");
			}
			
		System.out.println("\n---listar Artistas que se apresentarão na cidade de Campina Grande.");
		Query q2 = manager.query();
		q2.constrain(Artista.class);
		q2.descend("apresentacoes").descend("cidade").descend("nome").constrain("Campina Grande");
		List<Artista> resultados2 = q2.execute();
		
		if(resultados2.size()>0) {
			for(Artista a: resultados2) { 
				System.out.println(a);
			}
		}else{ 
			System.out.println("Nenhum Artista se apresenta na cidade.");
		}
		
	
		
		System.out.println("\n---listar o artista com o maior número de apresentações");
		Query q3 = manager.query();
		q3.constrain(Artista.class);
		q3.constrain(new Filtro());
		List<Artista> resultados3 = q3.execute();
		for(Artista a: resultados3)
			System.out.println(a);
		
	}
	
	//=================================================
	public static void main(String[] args) {
		new Consultar();
	}
}
class Filtro implements Evaluation {

	public void evaluate(Candidate candidate) {
		//destacar o objeto que esta sendo consultado no banco
		Artista artista = (Artista) candidate.getObject();
		
		if(artista.getApresentacoes().size()==2) 
			candidate.include(true); 	//incluir objeto no resultado da consulta
		else		
			candidate.include(false);	//excluir objeto do resultado da consulta
	}
}

