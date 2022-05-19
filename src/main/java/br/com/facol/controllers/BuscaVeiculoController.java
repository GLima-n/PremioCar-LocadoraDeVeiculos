package br.com.facol.controllers;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.facol.model.entidades.Veiculo;
import br.com.facol.model.servicos.VeiculoService;
import br.com.facol.model.util.Feedback;

@Named("buscaBean")
@ApplicationScoped
public class BuscaVeiculoController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private VeiculoService service;

	private List<Veiculo> veiculos;
	
	private String modelo;

	public BuscaVeiculoController() {

	}
	
	public List<Veiculo> listarPorModelo() {
		
		List<Veiculo> veiculos = this.service.listarPorModelo(modelo);
		
		if(veiculos.isEmpty()) {
			Feedback.warning("Veículo não encontrado");
			return null;
		}else {
		return veiculos;
		}
	}

	public List<Veiculo> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}


	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

}
