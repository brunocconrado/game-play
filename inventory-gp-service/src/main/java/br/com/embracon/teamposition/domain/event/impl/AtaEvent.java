package br.com.embracon.teamposition.domain.event.impl;

import br.com.embracon.teamposition.domain.entity.tmp.Ata;
import br.com.embracon.teamposition.domain.event.Event;

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
