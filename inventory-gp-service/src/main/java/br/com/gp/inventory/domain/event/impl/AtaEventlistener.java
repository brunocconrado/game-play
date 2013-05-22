package br.com.gp.inventory.domain.event.impl;

import org.springframework.stereotype.Component;

import br.com.embracon.j4e.util.DateUtils;
import br.com.gp.inventory.domain.entity.log.AtaLog;
import br.com.gp.inventory.domain.entity.tmp.Ata;
import br.com.gp.inventory.domain.enumeration.LogEnum;
import br.com.gp.inventory.domain.event.Event;
import br.com.gp.inventory.domain.event.EventListener;
import br.com.gp.inventory.domain.event.exception.EventListenerException;

@Component("ataListener")
public class AtaEventlistener implements EventListener<Ata> {

	public AtaEventlistener() {}

	@Override
	public void onSave(Event<Ata> event) throws EventListenerException {

		try {

			Ata ata = event.getEvent();

			AtaLog log = (AtaLog) ata.clone();

			if(ata.getId() == null) {
				log.setAction(LogEnum.CREATED);
			} else {
				log.setAction(LogEnum.UPDATED);
			}

			log.setRecord(DateUtils.getDate());
		} catch (CloneNotSupportedException e) {
			throw new EventListenerException(e);
		}
	}


}
