package br.com.facol.connectionfactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {
  
	private static EntityManagerFactory factory = null;
	private static EntityManager entityManager = null; 

	private ConnectionFactory() {
		
	}

	public static EntityManager getEntityManager() {

		if (factory == null) {
			factory = Persistence.createEntityManagerFactory("bd_premiocar");
		}
		 entityManager = factory.createEntityManager();
		 return entityManager;
	}


}
