package br.com.embracon.teamposition.domain.service;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.embracon.teamposition.domain.entity.Approvation;
import br.com.embracon.teamposition.domain.event.EventListener;
import br.com.embracon.teamposition.domain.event.Listener;

public interface ApprovationService {

	public Approvation findByBranchAndAta(Long branchId, Long ataId) throws ServiceException;

	public void save(Approvation approvation) throws ServiceException;
	
	@SuppressWarnings("rawtypes")
	public Listener<EventListener> getTrigger();

}
