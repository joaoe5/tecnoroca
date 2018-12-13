package tecno.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	// carregar configuração do persistence
	private static EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("aula-mysql");
	
	public static EntityManager getEntityManager(){
		//conecta com o banco
		return emf.createEntityManager();
	}
	
}
