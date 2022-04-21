package br.com.facol.model.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.facol.model.util.Identificador;

@Entity
@Table(name = "Reserva")
public class Reserva implements Serializable, Identificador {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(length = 10, nullable = false)
	private Date dataInicial;
	@Column(length = 10, nullable = false)
	private Date dataFinal;
	@Column(length = 50, nullable = false)
	private int totalDias;
	@Column(length = 50, nullable = false)
	private double valorTotal;
	

	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "veiculo_id")
	private Veiculo veiculo;

	@ManyToOne(cascade =  {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	public Reserva() {

	}

	public Reserva(Date dataInicial, Date dataFinal, int totalDias, double valorTotal, Veiculo veiculo,
			Usuario usuario) {
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.totalDias = totalDias;
		this.valorTotal = valorTotal;
		this.veiculo = veiculo;
		this.usuario = usuario;
	}


	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public int getTotalDias() {
		return totalDias;
	}

	public void setTotalDias(int totalDias) {
		this.totalDias = totalDias;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
