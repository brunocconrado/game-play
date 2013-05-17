package br.com.embracon.teamposition.domain.event.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.embracon.j4e.util.DateUtils;
import br.com.embracon.teamposition.domain.entity.log.AtaLog;
import br.com.embracon.teamposition.domain.entity.tmp.Ata;
import br.com.embracon.teamposition.domain.enumeration.LogEnum;
import br.com.embracon.teamposition.domain.event.Event;
import br.com.embracon.teamposition.domain.event.EventListener;
import br.com.embracon.teamposition.domain.event.exception.EventListenerException;
import br.com.embracon.teamposition.domain.repository.AtaLogRepository;

@Component("ataListener")
public class AtaEventlistener implements EventListener<Ata> {

	@Autowired
	@Qualifier("ataLogRepository")
	private AtaLogRepository logRepository;

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

			logRepository.save(log);
		} catch (CloneNotSupportedException e) {
			throw new EventListenerException(e);
		}
	}


}
