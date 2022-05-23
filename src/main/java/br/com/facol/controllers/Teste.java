package br.com.facol.controllers;

import br.com.facol.model.dao.VeiculoRepositorio;
import br.com.facol.model.entidades.Veiculo;

public class Teste {
	
	public static void main(String[] args) {
		VeiculoRepositorio repositorio = new VeiculoRepositorio();
		Veiculo v = new Veiculo();
		v.setAno(2022);
		v.setArcondicionado("fdsfdf");
		v.setCambioAutomatico("fdsgsfdg");
		v.setCor("rgewg");
		v.setFabricante("fdsdgf");
		v.setSituacao("Disponível");
		v.setModelo("ModeloTestando");
		v.setMotor(1.0);
		v.setPathImagem("/resources/imagens/2 celular.jpg");
		v.setPlaca("jkl0891");
		v.setQtdPortas(4);
		v.setTipo("jbjhgddt");
		v.setTipoDirecao("fshfdhgh");
		v.setValorDiaria(200);
		v.getId();
		repositorio.salvar(v);
	}

}
