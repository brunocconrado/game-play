package br.com.gp.inventory.domain.event.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.embracon.j4e.util.DateUtils;
import br.com.gp.inventory.domain.entity.log.BranchLog;
import br.com.gp.inventory.domain.entity.tmp.Branch;
import br.com.gp.inventory.domain.enumeration.LogEnum;
import br.com.gp.inventory.domain.event.Event;
import br.com.gp.inventory.domain.event.EventListener;
import br.com.gp.inventory.domain.event.exception.EventListenerException;
import br.com.gp.inventory.domain.repository.BranchLogRepository;
import br.com.gp.inventory.domain.vo.UserSession;

@Component("branchListener")
public class BranchEventListener implements EventListener<Branch> {
	
	@Autowired
	@Qualifier(value = "branchLogRepository")
	private BranchLogRepository logRepository;

	@Override
	public void onSave(Event<Branch> event) throws EventListenerException {
		
		try {
		
			Branch branch = event.getEvent();
			BranchLog log = (BranchLog) branch.clone(); 
			
			if(branch.getId() == null) {
				log.setAction(LogEnum.CREATED);
			} else {
				log.setAction(LogEnum.UPDATED);
			}
	
			log.setEditorRegistry(((UserSession)event.getEditor()).getRegistry());
			log.setRecord(DateUtils.getDate());
	
			logRepository.save(log);
		
		} catch (Exception e) {
			throw new EventListenerException(e);
		}
	}

}