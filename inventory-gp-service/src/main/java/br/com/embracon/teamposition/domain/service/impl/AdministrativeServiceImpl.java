package br.com.embracon.teamposition.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.embracon.j4e.domain.repository.RepositoryException;
import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.embracon.teamposition.domain.entity.Administrative;
import br.com.embracon.teamposition.domain.entity.Collaborator;
import br.com.embracon.teamposition.domain.repository.AdministrativeRepository;
import br.com.embracon.teamposition.domain.service.AdministrativeService;

@Component("administrativeService")
public class AdministrativeServiceImpl implements AdministrativeService {

	@Autowired
	@Qualifier("administrativeRepository")
	private AdministrativeRepository repository;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(Administrative administrative) throws ServiceException {
		
		try {
			
			this.repository.save(administrative);
			
			for(Collaborator collaborator : administrative.getColaborators()) {
				collaborator.setAdministrative(administrative);
			}
			
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
		
	}

}
