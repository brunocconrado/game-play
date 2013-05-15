package br.com.embracon.teamposition.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.embracon.j4e.domain.repository.RepositoryException;
import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.embracon.teamposition.domain.entity.Situation;
import br.com.embracon.teamposition.domain.enumeration.SituationEnum;
import br.com.embracon.teamposition.domain.repository.SituationRepository;
import br.com.embracon.teamposition.domain.service.SituationService;

@Component("situationService")
public class SituationServiceImpl implements SituationService {

	@Autowired
	@Qualifier("situationRepository")
	private SituationRepository repository;
	
	@Override
	public List<Situation> listSellerSituaions() throws ServiceException {
		try {
			return this.repository.findByType(Situation.SELLER);
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Situation> listSupervisorSituaions() throws ServiceException {
		try {
			return  this.repository.findByType(Situation.SUPERVISOR);
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Situation findBy(SituationEnum active) throws ServiceException {
		try {
			return  this.repository.findByIdentity(active.id());
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Situation findById(Long situationId) throws ServiceException {
		try {
			return  this.repository.findByIdentity(situationId);
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

}
