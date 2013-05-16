package br.com.embracon.teamposition.domain.service.impl;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.embracon.j4e.domain.repository.RepositoryException;
import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.embracon.teamposition.domain.entity.Status;
import br.com.embracon.teamposition.domain.enumeration.StatusEnum;
import br.com.embracon.teamposition.domain.repository.StatusRepository;
import br.com.embracon.teamposition.domain.service.StatusService;

@Component("statusService")
public class StatusServiceImpl implements StatusService {
	
	@Autowired
	@Qualifier("statusRepository")
	private StatusRepository repository;
	
	@Override
	public List<Status> findAllActives() throws ServiceException {
		
		try {
			List<Status> status = new LinkedList<Status>();
			for(Status s :  repository.findAll()) {
				if(s.getActive() == 'S') {
					status.add(s);
				}
			}
			
			Collections.sort(status);
			
			return status;
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public Status findBy(StatusEnum status) throws ServiceException {
		try {
			return repository.findBy(status);
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}
	
}
