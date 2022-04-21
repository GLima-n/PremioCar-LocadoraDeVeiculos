package br.com.facol.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.facol.model.entidades.Endereco;
import br.com.facol.model.entidades.Usuario;
import br.com.facol.model.exceptions.EnderecoException;
import br.com.facol.model.exceptions.UsuarioException;
import br.com.facol.model.servicos.UsuarioService;
import br.com.facol.model.util.Feedback;

@Named("usuarioBean")
@ViewScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public UsuarioController() {
		
	}
	@Inject
	private Endereco endereco;

	@Inject
	private Usuario usuario;
	
	@Inject
	private UsuarioService service;
	
	private List<Usuario> usuarios;

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	@PostConstruct
	public void carregar() {
		this.usuarios = service.listar();
	}
	
	
	public void salvar() {
		try {
			this.usuario.setEndereco(endereco);
			this.service.salvar(usuario);
			 usuario = new Usuario();
			 endereco = new Endereco();
			 carregar();
			 Feedback.info("Usuário Salvo Com Sucesso!");
		} catch (UsuarioException e) {
			 Feedback.erro(e.getMessage());
		} catch (EnderecoException e) {
			Feedback.erro(e.getMessage());
		}
	}
	
	public void excluir() {
		try {
			this.service.excluir(usuario);
			carregar();
			Feedback.info(usuario.getNome() + " Foi Excluído Com Sucesso!");
		} catch (UsuarioException e) {
			e.printStackTrace();
			Feedback.erro(e.getMessage());
		}
	}

}
