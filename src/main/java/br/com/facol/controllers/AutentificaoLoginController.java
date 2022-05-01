package br.com.facol.controllers;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.facol.model.entidades.Usuario;
import br.com.facol.model.util.AutentificarLogin;
import br.com.facol.model.util.Feedback;

@Named("autentificarBean")
@RequestScoped
public class AutentificaoLoginController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private AutentificarLogin autentificar;
	
	private String email;
	private String senha;
	
	public AutentificaoLoginController() {
		
	}
	
	
	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getSenha() {
		return senha;
	}



	public void setSenha(String senha) {
		this.senha = senha;
	}



	public String autentificar() {
		Usuario usuario = this.autentificar.Autentificar(email, senha);
		
		if(usuario == null) {
			Feedback.warning("Usuário não encontrado");
			return null;
		}else {
			return "TelaPrincipalFuncionario.xhtml?faces-redirect=true";
		}
		
	}

	public AutentificarLogin getAutentificar() {
		return autentificar;
	}

	public void setAutentificar(AutentificarLogin autentificar) {
		this.autentificar = autentificar;
	}
	
	

}