package br.com.facol.model.dao;

import javax.persistence.EntityManager;

import br.com.facol.connectionfactory.ConnectionFactory;
import br.com.facol.model.entidades.Usuario;
import br.com.facol.model.exceptions.UsuarioException;
import br.com.facol.model.util.Feedback;

public class UsuarioRepositorio extends RepositorioGenerico<Usuario> {

	private static final long serialVersionUID = 1L;

	private EntityManager entityManager = ConnectionFactory.getEntityManager();

	public UsuarioRepositorio() {

	}
	
	@Override
	public void salvar(Usuario t)  {
		try {
			this.entityManager.getTransaction().begin();
			if (t.getId() == null) {
				for (Usuario usuario : listarTudo(Usuario.class, "Select u from Usuario u")) {
					if (usuario.getCpf().equals(t.getCpf())) {
						throw new UsuarioException("CPF já cadastrado");
						
					}
					
					if (usuario.getEmail().equals(t.getEmail())) {
						throw new UsuarioException("E-mail já cadastrado");
					}

				}
				this.entityManager.persist(t);
				Feedback.info("Cadastrado com sucesso");
			} else {
				this.entityManager.merge(t);
				Feedback.info("Alterado com sucesso");
				
			}
			this.entityManager.getTransaction().commit();
		} catch (

		RuntimeException e) {
			this.entityManager.getTransaction().rollback();
			e.printStackTrace();
		} catch (UsuarioException e) {
			Feedback.erro(e.getMessage());
		}
	}

	public Usuario buscarPorCPF(String cpf) {
		try {
			Usuario usuario = (Usuario) entityManager.createQuery("SELECT u FROM Usuario u WHERE u.cpf = :cpf")
					.setParameter("cpf", cpf).getSingleResult();
			return usuario;

		} catch (RuntimeException e) {
			e.printStackTrace();
			return null;
		}
	}

}
