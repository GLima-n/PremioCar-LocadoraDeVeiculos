package br.com.facol.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.facol.connectionfactory.ConnectionFactory;
import br.com.facol.model.entidades.Veiculo;
import br.com.facol.model.util.Feedback;

public class VeiculoRepositorio extends RepositorioGenerico<Veiculo> {

	private static final long serialVersionUID = 1L;

	private EntityManager entityManager = ConnectionFactory.getEntityManager();

	@Override
	public void salvar(Veiculo t) {
		try {
			this.entityManager.getTransaction().begin();
			if (t.getId() == null) {
				
				for(Veiculo veiculo : listarTudo(Veiculo.class, "Select u from Veiculo u")) {
					if(veiculo.getPlaca().equals(t.getPlaca())) {
						Feedback.erro("Placa já cadastrada!");
					}
				}
				this.entityManager.persist(t);
				Feedback.info("Cadastrado com sucesso!");
			
			} else {
				this.entityManager.merge(t);
				Feedback.info("Alterado com sucesso!");
				
			}
			this.entityManager.getTransaction().commit();
		} catch (

		RuntimeException e) {
			this.entityManager.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	public Veiculo buscarVeiculoPlaca(String placa) {
		try {
			Veiculo veiculo = (Veiculo) entityManager.createQuery("SELECT u FROM Veiculo u WHERE u.placa = :placa")
					.setParameter("placa", placa).getSingleResult();
			return veiculo;

		} catch (RuntimeException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Veiculo> listarPorModelo(String modelo) {
		try {
			TypedQuery<Veiculo> query = this.entityManager
					.createQuery("SELECT u FROM Veiculo u WHERE u.modelo LIKE :modelo", Veiculo.class)
					.setParameter("modelo", modelo);
			List<Veiculo> resultado = query.getResultList();
			return resultado;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return null;
		}
	}

}
