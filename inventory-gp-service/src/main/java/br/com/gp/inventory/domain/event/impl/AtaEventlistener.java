package br.com.gp.inventory.domain.event.impl;

import org.springframework.stereotype.Component;

@Component("ataListener")
public class AtaEventlistener /*implements EventListener<Ata>*/ {

	/*public AtaEventlistener() {}

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
	}*/


}
