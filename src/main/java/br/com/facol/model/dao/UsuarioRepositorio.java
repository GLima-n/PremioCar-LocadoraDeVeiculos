package br.com.facol.model.dao;

import javax.persistence.EntityManager;

import br.com.facol.connectionfactory.ConnectionFactory;
import br.com.facol.model.entidades.Usuario;

public class UsuarioRepositorio extends RepositorioGenerico<Usuario> {

	private static final long serialVersionUID = 1L;
	
	private EntityManager entityManager = ConnectionFactory.getEntityManager();
	
	
	public UsuarioRepositorio() {
		
	}

	public Usuario buscarPorCPF(String cpf) {
		try {
			Usuario usuario = (Usuario) entityManager
					.createQuery("SELECT u FROM Usuario u WHERE u.cpf = :cpf")
					.setParameter("cpf", cpf).getSingleResult();
			return usuario;


		} catch (RuntimeException e) {
			e.printStackTrace();
			return null;
		}
	}

}
