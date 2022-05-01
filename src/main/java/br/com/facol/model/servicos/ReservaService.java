package br.com.facol.model.servicos;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;


import br.com.facol.model.dao.ReservaRepositorio;
import br.com.facol.model.entidades.Reserva;
import br.com.facol.model.exceptions.ReservaException;

public class ReservaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	ReservaRepositorio repositorioReserva;

	public void salvarReserva(Reserva reserva) throws ReservaException {
		if (reserva != null) {

			this.repositorioReserva.salvar(reserva);

		} else {
			throw new ReservaException("Veículo não encontrado ");
		}

	}

	public Reserva listarReservaPorId(long id) {
		return this.repositorioReserva.buscarReservaId(id);
	}

	public List<Reserva> listarTudo() {
		return this.repositorioReserva.listarTudo(Reserva.class,
				"SELECT r FROM Reserva r INNER JOIN r.veiculo  WHERE r.veiculo = r.veiculo.id");
	}

}
