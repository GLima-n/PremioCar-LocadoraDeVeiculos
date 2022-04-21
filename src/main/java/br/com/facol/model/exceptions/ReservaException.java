package br.com.facol.model.exceptions;

import java.io.Serializable;

public class ReservaException extends Exception implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public ReservaException(String erro) {
		super(erro);
	}

}
