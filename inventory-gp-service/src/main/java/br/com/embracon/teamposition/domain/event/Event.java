package br.com.embracon.teamposition.domain.event;

public interface Event<E> {
	
	public E getEvent();
	
	public <C> C getEditor();
	
}
