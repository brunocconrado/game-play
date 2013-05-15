package br.com.gp.inventory.domain.event.impl;

import br.com.gp.inventory.domain.entity.Ata;
import br.com.gp.inventory.domain.event.Event;

public class AtaEvent implements Event<Ata> {
	
	private Ata ata;
	
	public AtaEvent(Ata ata) {
		this.ata = ata;
	}

	@Override
	public Ata getEvent() {
		return this.ata;
	}

	@Override
	public <C> C getEditor() {
		return null;
	}
	
	

}
