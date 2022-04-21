package br.com.facol.model.util;

import javax.persistence.EntityManager;

import br.com.facol.connectionfactory.ConnectionFactory;
import br.com.facol.model.entidades.Usuario;

public class AutentificarLogin {

	private EntityManager entityManager = ConnectionFactory.getEntityManager();

	public Usuario Autentificar(String email, String senha) {

		try {
			Usuario usuario = (Usuario) this.entityManager
					.createQuery("Select c from Usuario c WHERE c.email = :email and c.senha = :senha")
					.setParameter("senha", senha).setParameter("email", email).getSingleResult();
			System.out.println("Achei");
			return usuario;

		} catch (RuntimeException e) {
			System.out.println(e.toString());
			return null;

		}

	}
}