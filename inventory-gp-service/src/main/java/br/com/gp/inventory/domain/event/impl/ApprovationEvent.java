package br.com.gp.inventory.domain.event.impl;

import br.com.gp.inventory.domain.entity.tmp.Approvation;
import br.com.gp.inventory.domain.event.Event;

public class ApprovationEvent implements Event<Approvation> {
	
	private Approvation approvation;
	
	public ApprovationEvent(Approvation approvation) {
		this.approvation = approvation;
	}

	@Override
	public Approvation getEvent() {
		return this.approvation;
	}

	@Override
	public <C> C getEditor() {
		return null;
	}
}
