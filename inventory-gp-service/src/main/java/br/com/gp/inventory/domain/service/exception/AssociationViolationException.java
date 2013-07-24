package br.com.gp.inventory.domain.service.exception;

import br.com.embracon.j4e.services.exception.ServiceException;

public class AssociationViolationException extends ServiceException {
	
	private static final long serialVersionUID = 7000842828238128684L;

	public AssociationViolationException() {
		super();
	}

	public AssociationViolationException(String message, Throwable cause) {
		super(message, cause);
	}

	public AssociationViolationException(String message) {
		super(message);
	}

	public AssociationViolationException(Throwable cause) {
		super(cause);
	}

}
