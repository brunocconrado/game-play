package br.com.gp.inventory.domain.event;

import br.com.gp.inventory.domain.event.exception.EventListenerException;

public interface EventListener<E> {
	
	public void onSave(Event<E> event) throws EventListenerException;
	
}
