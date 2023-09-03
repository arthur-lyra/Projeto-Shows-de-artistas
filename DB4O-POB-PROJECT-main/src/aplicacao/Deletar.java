package aplicacao;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import modelo.Apresentacao;


public class Deletar {
	protected ObjectContainer manager;

	public Deletar(){
		manager = Util.conectarBanco();
		apagar();
		Util.desconectar();
		
		System.out.println("\n\n aviso: feche sempre o plugin OME antes de executar aplicação");
	}

	public void apagar(){
		//localizar a apresentação de id 2
		Query q = manager.query();
		q.constrain(Apresentacao.class);
		q.descend("id").constrain(2);
		List<Apresentacao> resultados = q.execute();
	
		if(resultados.size()>0) {
			//apagar a apresentação
			Apresentacao a =  resultados.get(0);
			manager.delete(a);
			manager.commit();
			System.out.println("apagou a Apresentação 2 mas não apagou sua cidade nem seu artista");
		}
		else
			System.out.println("Apresentação inexistente");
	}



	//=================================================
	public static void main(String[] args) {
		new Deletar();
	}
}

