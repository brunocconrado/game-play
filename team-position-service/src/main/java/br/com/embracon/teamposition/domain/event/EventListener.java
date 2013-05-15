package br.com.embracon.teamposition.domain.event;

import br.com.embracon.teamposition.domain.event.exception.EventListenerException;

public interface EventListener<E> {
	
	public void onSave(Event<E> event) throws EventListenerException;
	
}
