package br.com.facol.model.entidades;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Usuario extends Pessoa{

	private static final long serialVersionUID = 1L;
	
	@Column(length = 50, nullable = false, unique = true)
	private String email;
	@Column(length = 50, nullable = false)
	private String senha;
    
	@OneToMany(mappedBy = "usuario", cascade = {CascadeType.REMOVE, CascadeType.MERGE})
	private List<Reserva> reservas;
	
	
	public Usuario() {

	}

	public Usuario(String cpf, String nome, String sobrenome, String telefone, Endereco endereco, String email,
			String senha) {
		super(cpf, nome, sobrenome, telefone, endereco);
		this.email = email;
		this.senha = senha;
	
	}



	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
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

	@Override
	public String toString() {
		return "Usuario" + "Nome: " + getNome() + "CPF: " + getCpf() + "Telefone: " + getTelefone();
	}


}
