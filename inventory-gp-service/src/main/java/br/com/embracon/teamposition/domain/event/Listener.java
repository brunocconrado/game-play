package br.com.embracon.teamposition.domain.event;

public interface Listener<L> {

	public void addListener(L listener);
	
	public void removeListener(L listener);
	
}
