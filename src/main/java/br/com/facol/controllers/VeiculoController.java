package br.com.facol.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.facol.model.entidades.Veiculo;
import br.com.facol.model.exceptions.VeiculoException;
import br.com.facol.model.servicos.VeiculoService;
import br.com.facol.model.util.Feedback;
import br.com.facol.model.util.UploadImagem;

@Named("veiculoBean")
@ViewScoped
public class VeiculoController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Veiculo veiculo;

	@Inject
	private VeiculoService service;

	@Inject
	private UploadImagem upload;

	private List<Veiculo> veiculos;
	
	private long id;
	

	public Long pegarId() {
		String nid = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idVeiculo");
		return this.id = Long.parseLong(nid);
	}
	
	 public List<Veiculo> fetchCurrentList(int from, int to)
	    {
	        return veiculos.subList(from, to);
	    }

	    public int getListSize()
	    {
	        return this.veiculos.size();
	    }

	public Veiculo getVeiculo() {
		return veiculo;
	}
	
	

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public List<Veiculo> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UploadImagem getUpload() {
		return upload;
	}

	public void setUpload(UploadImagem upload) {
		this.upload = upload;
	}

	@PostConstruct
	public void carregar() {
		this.veiculos = service.listar();

		
	}
	
	public void salvar() {
		try {
			this.veiculo.setPathImagem(upload.getFile().getName());
			this.veiculo.setSituacao("Disponível");
			this.service.salvar(veiculo);
			this.veiculo = new Veiculo();
			carregar();
			
		} catch (VeiculoException e) {
			Feedback.erro(e.getMessage());
		}catch(NullPointerException e) {
			e.printStackTrace();
			
		}
	}

	public Veiculo listarPorId() {
		Veiculo veiculo = service.listarPorId(pegarId());
		return veiculo;
	}
	

	public void excluir() {
		try {
			this.service.excluir(veiculo);
			carregar();
		} catch (VeiculoException e) {
			Feedback.erro(e.getMessage());
		}
	}

}
