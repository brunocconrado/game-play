package br.com.gp.inventory.domain.event.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.embracon.j4e.util.DateUtils;
import br.com.gp.inventory.domain.entity.log.ApprovationLog;
import br.com.gp.inventory.domain.entity.tmp.Approvation;
import br.com.gp.inventory.domain.event.Event;
import br.com.gp.inventory.domain.event.EventListener;
import br.com.gp.inventory.domain.event.exception.EventListenerException;
import br.com.gp.inventory.domain.repository.ApprovationLogRepository;

@Component("approvationListener")
public class ApprovationListener implements EventListener<Approvation> {

	@Autowired
	@Qualifier("approvationLogRepository")
	private ApprovationLogRepository logRepository;
	
	@Override
	public void onSave(Event<Approvation> event) throws EventListenerException {
		
		try {
			
			Approvation aproApprovation = event.getEvent();
			ApprovationLog log = (ApprovationLog) aproApprovation.clone();
			log.setRecord(DateUtils.getDate());
			
			logRepository.save(log);
			
		} catch (Exception e) {
			throw new EventListenerException(e);
		}
	}
}
