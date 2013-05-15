package br.com.embracon.teamposition.domain.service;

import java.util.List;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.embracon.teamposition.domain.entity.Status;
import br.com.embracon.teamposition.domain.enumeration.StatusEnum;

public interface StatusService {
	
	public List<Status> findAllActives() throws ServiceException;
	
	public Status findBy(StatusEnum status) throws ServiceException;

}
