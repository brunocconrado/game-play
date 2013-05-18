package br.com.gp.inventory.domain.event;

public interface Event<E> {
	
	public E getEvent();
	
	public <C> C getEditor();
	
}
