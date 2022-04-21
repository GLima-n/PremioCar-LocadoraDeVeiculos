package br.com.facol.model.exceptions;

import java.io.Serializable;

public class VeiculoException extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;

	public VeiculoException(String erro) {
		super(erro);
	}
}
