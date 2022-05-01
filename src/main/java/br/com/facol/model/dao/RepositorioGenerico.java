package br.com.facol.model.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.facol.connectionfactory.ConnectionFactory;
import br.com.facol.model.util.Identificador;

public abstract class RepositorioGenerico<T extends Identificador> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private EntityManager entityManager = ConnectionFactory.getEntityManager();

	public RepositorioGenerico() {

	}
	public void salvar(T t) {
		try {
			this.entityManager.getTransaction().begin();
			if (t.getId() == null) {
				this.entityManager.persist(t);
			} else {
				this.entityManager.merge(t);
			}
			this.entityManager.getTransaction().commit();
		} catch (RuntimeException e) {
			this.entityManager.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	public T buscarPorId(Class<T> classe, Long id) {
		try {
			T t = this.entityManager.find(classe, id);
			return t;

		} catch (RuntimeException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<T> listarTudo(Class<T> classe, String jpql) {
		try {
			TypedQuery<T> query = this.entityManager.createQuery(jpql, classe);
			List<T> resultado = query.getResultList();
			return resultado;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void excluir(Class<T> classe, Long id) {
		try {
			this.entityManager.getTransaction().begin();
			this.entityManager.remove(entityManager.getReference(classe, id));
			this.entityManager.getTransaction().commit();
		} catch (RuntimeException e) {
			this.entityManager.getTransaction().rollback();
			e.printStackTrace();
		}
	}

}
