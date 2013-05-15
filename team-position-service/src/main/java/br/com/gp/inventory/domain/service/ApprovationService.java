package br.com.gp.inventory.domain.service;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Approvation;
import br.com.gp.inventory.domain.event.EventListener;
import br.com.gp.inventory.domain.event.Listener;

public interface ApprovationService {

	public Approvation findByBranchAndAta(Long branchId, Long ataId) throws ServiceException;

	public void save(Approvation approvation) throws ServiceException;
	
	@SuppressWarnings("rawtypes")
	public Listener<EventListener> getTrigger();

}
