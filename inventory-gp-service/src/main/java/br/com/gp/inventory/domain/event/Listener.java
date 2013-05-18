package br.com.gp.inventory.domain.event;

public interface Listener<L> {

	public void addListener(L listener);
	
	public void removeListener(L listener);
	
}
