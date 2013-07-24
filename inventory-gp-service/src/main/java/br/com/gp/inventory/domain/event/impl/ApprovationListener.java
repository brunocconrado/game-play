package br.com.gp.inventory.domain.event.impl;

import org.springframework.stereotype.Component;

@Component("approvationListener")
public class ApprovationListener/* implements EventListener<Approvation>*/ {

	/*@Override
	public void onSave(Event<Approvation> event) throws EventListenerException {
		
		try {
			
			Approvation aproApprovation = event.getEvent();
			ApprovationLog log = (ApprovationLog) aproApprovation.clone();
			log.setRecord(DateUtils.getDate());
			
		} catch (Exception e) {
			throw new EventListenerException(e);
		}
	}*/
}
