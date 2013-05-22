package br.com.gp.inventory.domain.event.impl;

import org.springframework.stereotype.Component;

import br.com.embracon.j4e.util.DateUtils;
import br.com.gp.inventory.domain.entity.log.ApprovationLog;
import br.com.gp.inventory.domain.entity.tmp.Approvation;
import br.com.gp.inventory.domain.event.Event;
import br.com.gp.inventory.domain.event.EventListener;
import br.com.gp.inventory.domain.event.exception.EventListenerException;

@Component("approvationListener")
public class ApprovationListener implements EventListener<Approvation> {

	@Override
	public void onSave(Event<Approvation> event) throws EventListenerException {
		
		try {
			
			Approvation aproApprovation = event.getEvent();
			ApprovationLog log = (ApprovationLog) aproApprovation.clone();
			log.setRecord(DateUtils.getDate());
			
		} catch (Exception e) {
			throw new EventListenerException(e);
		}
	}
}
