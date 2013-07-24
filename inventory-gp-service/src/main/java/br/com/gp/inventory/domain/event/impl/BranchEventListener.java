package br.com.gp.inventory.domain.event.impl;

import org.springframework.stereotype.Component;

@Component("branchListener")
public class BranchEventListener /*implements EventListener<Branch>*/ {
	
	/*@Override
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
		
		} catch (Exception e) {
			throw new EventListenerException(e);
		}
	}*/

}
