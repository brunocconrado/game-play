package br.com.gp.inventory.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.embracon.j4e.domain.repository.RepositoryException;
import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Administrative;
import br.com.gp.inventory.domain.entity.Collaborator;
import br.com.gp.inventory.domain.service.AdministrativeService;
import br.com.gp.inventory.domain.service.CollaboratorService;

@Component("administrativeService")
public class AdministrativeServiceImpl implements AdministrativeService {

	
	@Autowired
	@Qualifier("collaboratorService")
	private CollaboratorService collaboratorService;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(Administrative administrative) throws ServiceException {
		
		try {
			
			
			for(Collaborator collaborator : administrative.getColaborators()) {
				collaborator.setAdministrative(administrative);
				collaboratorService.save(collaborator);
			}
			
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
		
	}

}
