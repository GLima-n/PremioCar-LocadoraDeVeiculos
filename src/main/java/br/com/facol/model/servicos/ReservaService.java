package br.com.facol.model.servicos;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.facol.model.dao.DaoGenerico;
import br.com.facol.model.entidades.Reserva;
import br.com.facol.model.exceptions.ReservaException;

public class ReservaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	DaoGenerico<Reserva> repositorioReserva;

	public void salvarReserva(Reserva reserva) throws ReservaException {
		if (reserva != null) {
			this.repositorioReserva.salvar(reserva);
		} else {
			throw new ReservaException("Veículo não encontrado ");
		}

	}
	
	public Reserva listarReservaPorPlaca(String placa) {
		return this.repositorioReserva.buscarReservaPlaca(placa);
	}

}
