
package aplicacao;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;

import modelo.Apresentacao;
import modelo.Artista;
import modelo.Cidade;

public class Util {
	private static ObjectContainer manager;

	public static ObjectContainer conectarBanco(){
		if (manager != null)
			return manager;		//ja tem uma conexao
		//---------------------------------------------------------------
		//configurar, criar e abrir banco local (pasta do projeto)
		//---------------------------------------------------------------
		
		EmbeddedConfiguration config =  Db4oEmbedded.newConfiguration(); 
		config.common().messageLevel(0);  // mensagens na tela 0(desliga),1,2,3...
		
		// habilitar cascata para alterar, apagar e recuperar objetos
		config.common().objectClass(Apresentacao.class).cascadeOnDelete(false);;
		config.common().objectClass(Apresentacao.class).cascadeOnUpdate(true);;
		config.common().objectClass(Apresentacao.class).cascadeOnActivate(true);
		
		config.common().objectClass(Artista.class).cascadeOnDelete(false);;
		config.common().objectClass(Artista.class).cascadeOnUpdate(true);;
		config.common().objectClass(Artista.class).cascadeOnActivate(true);
		
		config.common().objectClass(Cidade.class).cascadeOnDelete(false);;
		config.common().objectClass(Cidade.class).cascadeOnUpdate(true);;
		config.common().objectClass(Cidade.class).cascadeOnActivate(true);
		
		//conexao local
		manager = Db4oEmbedded.openFile(config, "banco.db4o");
		return manager;
	}

	public static void desconectar() {
		if(manager!=null) {
			manager.close();
			manager=null;
		}
	}
	
}
