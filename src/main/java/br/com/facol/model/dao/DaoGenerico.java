package br.com.facol.model.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


import br.com.facol.connectionfactory.ConnectionFactory;
import br.com.facol.model.entidades.Reserva;
import br.com.facol.model.entidades.Usuario;
import br.com.facol.model.entidades.Veiculo;
import br.com.facol.model.util.Identificador;

public class DaoGenerico<T extends Identificador> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private EntityManager entityManager = ConnectionFactory.getEntityManager();

	public DaoGenerico() {

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
	
	public Reserva buscarReservaPlaca(String placa) {
		try {
			Reserva reserva = (Reserva) entityManager
					.createQuery("SELECT u FROM Reserva u INNER JOIN u.veiculo c WHERE c.placa = :placa")
					.setParameter("placa", placa).getSingleResult();
			return reserva;


		} catch (RuntimeException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Veiculo buscarVeiculoPlaca(String placa) {
		try {
			Veiculo veiculo = (Veiculo) entityManager
					.createQuery("SELECT u FROM Veiculo u WHERE u.placa = :placa")
					.setParameter("placa", placa).getSingleResult();
			return veiculo;


		} catch (RuntimeException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Veiculo> listarPorModelo(String modelo) {
		try {
			TypedQuery<Veiculo> query = this.entityManager.createQuery("SELECT u FROM Veiculo u WHERE u.modelo LIKE :modelo", Veiculo.class)
			.setParameter("modelo", modelo);
			List<Veiculo> resultado = query.getResultList();
			return resultado;
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
