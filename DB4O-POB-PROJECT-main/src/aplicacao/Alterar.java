package aplicacao;

import java.util.List;


import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import modelo.Apresentacao;
import modelo.Cidade;


public class Alterar {
	protected ObjectContainer manager;

	public Alterar(){
		manager = Util.conectarBanco();
		atualizar();
		Util.desconectar();

		System.out.println("\n\n aviso: feche sempre o plugin OME antes de executar aplica��o");
	}

	public void atualizar(){
		//localizar apresentação com id 1
		Query q = manager.query();
		q.constrain(Apresentacao.class);  				
		q.descend("id").constrain(1);
		List<Apresentacao> resultados = q.execute(); 
		
		Query q2 = manager.query();
		q2.constrain(Cidade.class);  				
		q2.descend("nome").constrain("Campina Grande");
		List<Cidade> resultados2 = q2.execute(); 

		if(resultados.size()>0) {
			//alterar data, cidade e o preço do ingresso de uma apresentação
			Apresentacao a =  resultados.get(0);
			a.setData("25/09/2023");
			a.setPrecoIngresso(90);
			if(resultados2.size()>0) {
				Cidade c =  resultados2.get(0);
				a.setCidade(c);
			}
			manager.store(a);
			manager.commit();
			System.out.println("alterou a Data para 25/09/2023 em Campina Grande no valor de 90 reais.");
		}
		else
			System.out.println("Apresentação inexistente"); 
	}



	//=================================================
	public static void main(String[] args) {
		new Alterar();
	}
}

