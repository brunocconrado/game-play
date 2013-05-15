package br.com.gp.inventory.domain.application.exception;

import java.io.IOException;

@SuppressWarnings("serial")
public class ApplicationException extends RuntimeException {

	public ApplicationException(String mensagem) {
		super(mensagem);
	}

	public ApplicationException(IOException e) {
		super(e);
	}

	public ApplicationException(Throwable t) {
		super(t);
	}
	
}
