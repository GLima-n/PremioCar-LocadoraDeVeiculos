package br.com.facol.model.entidades;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.com.facol.model.util.Identificador;

@Entity
public class Veiculo implements Identificador, Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 50, nullable = false, unique = true)
	private String placa;
	@Column(length = 50, nullable = false)
	private String modelo;
	@Column(length = 50, nullable = false)
	private String fabricante;
	@Column(length = 50, nullable = false)
	private Integer ano;
	@Column(length = 50, nullable = false)
	private String cor;
	@Column(length = 50, nullable = false)
	private Double motor;
	@Column(length = 50, nullable = false)
	private int qtdPortas;
	@Column(length = 50, nullable = false)
	private String arcondicionado;
	@Column(length = 50, nullable = false)
	private String tipoDirecao;
	@Column(length = 50, nullable = false)
	private String cambioAutomatico;
	@Column(length = 50, nullable = false)
	private String tipo;
	@Column(length = 50, nullable = false)
	private double valorDiaria;
	@Column(length = 50, nullable = false)
	private String pathImagem;
	@Column(length = 50, nullable = false)
	private String situacao;
	
	@OneToOne(mappedBy = "veiculo", cascade = {CascadeType.REMOVE, CascadeType.MERGE})
	private Reserva reserva;

	public Veiculo() {

	}

	public Veiculo(String placa, String modelo, String fabricante, Integer ano, String cor, Double motor, int qtdPortas,
			String arcondicionado, String tipoDirecao, String cambioAutomatico, String tipo, double valorDiaria,
			String pathImagem, String situacao) {
		super();
		this.placa = placa;
		this.modelo = modelo;
		this.fabricante = fabricante;
		this.ano = ano;
		this.cor = cor;
		this.motor = motor;
		this.qtdPortas = qtdPortas;
		this.arcondicionado = arcondicionado;
		this.tipoDirecao = tipoDirecao;
		this.cambioAutomatico = cambioAutomatico;
		this.tipo = tipo;
		this.valorDiaria = valorDiaria;
		this.pathImagem = pathImagem;
		this.situacao = situacao;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getPathImagem() {
		return pathImagem;
	}

	public void setPathImagem(String pathImagem) {
		this.pathImagem = pathImagem;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getQtdPortas() {
		return qtdPortas;
	}

	public void setQtdPortas(int qtdPortas) {
		this.qtdPortas = qtdPortas;
	}

	public String getArcondicionado() {
		return arcondicionado;
	}

	public void setArcondicionado(String arcondicionado) {
		this.arcondicionado = arcondicionado;
	}

	public String getTipoDirecao() {
		return tipoDirecao;
	}

	public void setTipoDirecao(String tipoDirecao) {
		this.tipoDirecao = tipoDirecao;
	}

	public String getCambioAutomatico() {
		return cambioAutomatico;
	}

	public void setCambioAutomatico(String cambioAutomatico) {
		this.cambioAutomatico = cambioAutomatico;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Double getMotor() {
		return motor;
	}

	public double getValorDiaria() {
		return valorDiaria;
	}

	public void setValorDiaria(double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}

	public void setMotor(Double potenciaMotor) {
		this.motor = potenciaMotor;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	@Override
	public String toString() {
		return "Veiculo [placa=" + placa + ", modelo=" + modelo + ", fabricante=" + fabricante + ", ano=" + ano
				+ ", cor=" + cor + "]";
	}

}
