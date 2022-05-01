package br.com.facol.model.servicos;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.facol.model.dao.VeiculoRepositorio;
import br.com.facol.model.entidades.Veiculo;
import br.com.facol.model.exceptions.VeiculoException;

public class VeiculoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private VeiculoRepositorio repositorio;

	public void salvar(Veiculo veiculo) throws VeiculoException {
		     
			if (veiculo.getPlaca().length() < 7) {
				throw new VeiculoException("Placa inválida. Insira uma placa com 7 caracteres");
			}

			if (veiculo.getArcondicionado() == null) {
				throw new VeiculoException("Campo arcondicionado vazio");
			}

			if (veiculo.getModelo().length() < 3) {
				throw new VeiculoException("Valor inválido. Insira um valor maior que 3 caracteres");
			}

			if (veiculo.getAno().toString().length() < 4) {
				throw new VeiculoException("Informe um ano com 4 dígitos");
			}

			if (veiculo.getCor().length() < 4) {
				throw new VeiculoException("Informe uma cor com 4 caracteres");
			} else {
				this.repositorio.salvar(veiculo);
			}
	}

	public void excluir(Veiculo veiculo) throws VeiculoException {
		if (veiculo.getId() != null) {
			this.repositorio.excluir(Veiculo.class, veiculo.getId());
		} else {
			throw new VeiculoException("Veículo não existe!");
		}
	}

	public Veiculo listarPorId(long id) {
		Veiculo veiculo = repositorio.buscarPorId(Veiculo.class, id);
		return veiculo;
	}

	public List<Veiculo> listarPorModelo(String modelo) {

		return this.repositorio.listarPorModelo(modelo);

	}

	public List<Veiculo> listar() {
		return this.repositorio.listarTudo(Veiculo.class, "Select u from Veiculo u");
	}
}
