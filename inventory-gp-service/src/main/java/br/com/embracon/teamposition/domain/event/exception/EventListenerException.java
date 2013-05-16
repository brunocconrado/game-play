package br.com.embracon.teamposition.domain.event.exception;

public class EventListenerException extends Exception {

	private static final long serialVersionUID = 3131017411332778367L;

	public EventListenerException() {
		super();
	}

	public EventListenerException(String message, Throwable cause) {
		super(message, cause);
	}

	public EventListenerException(String message) {
		super(message);
	}

	public EventListenerException(Throwable cause) {
		super(cause);
	}
}
