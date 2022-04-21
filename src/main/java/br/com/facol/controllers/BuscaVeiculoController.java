package br.com.facol.controllers;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.facol.model.entidades.Veiculo;
import br.com.facol.model.servicos.VeiculoService;

@Named("buscaBean")
@ApplicationScoped
public class BuscaVeiculoController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private VeiculoService service;

	public BuscaVeiculoController() {

	}

	private String modelo;

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public List<Veiculo> listarPorModelo() {

		List<Veiculo> veiculos = this.service.listarPorModelo(modelo);
		return veiculos;

	}

}
