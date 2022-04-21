package br.com.facol.model.exceptions;

import java.io.Serializable;

public class EnderecoException extends Exception implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public EnderecoException(String erro) {
		super(erro);
	}

}
