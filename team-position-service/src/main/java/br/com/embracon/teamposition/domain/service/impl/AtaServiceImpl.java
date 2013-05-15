package br.com.embracon.teamposition.domain.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.embracon.j4e.domain.repository.RepositoryException;
import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.embracon.j4e.util.DateUtils;
import br.com.embracon.j4e.validation.LocalizableReason;
import br.com.embracon.j4e.validation.ValidationException;
import br.com.embracon.j4e.validation.ValidationResult;
import br.com.embracon.teamposition.domain.entity.Ata;
import br.com.embracon.teamposition.domain.entity.AtaExcecao;
import br.com.embracon.teamposition.domain.event.EventListener;
import br.com.embracon.teamposition.domain.event.EventTrigger;
import br.com.embracon.teamposition.domain.event.Listener;
import br.com.embracon.teamposition.domain.event.exception.EventListenerException;
import br.com.embracon.teamposition.domain.event.impl.AtaEvent;
import br.com.embracon.teamposition.domain.repository.AtaRepository;
import br.com.embracon.teamposition.domain.search.AtaSearch;
import br.com.embracon.teamposition.domain.service.AtaExcecaoService;
import br.com.embracon.teamposition.domain.service.AtaService;
import br.com.embracon.teamposition.domain.validator.AtaValidator;

@Component("ataService")
@SuppressWarnings({"rawtypes","unchecked"})
public class AtaServiceImpl implements AtaService {

	@Autowired
	@Qualifier(value = "ataRepository")
	private AtaRepository repository; 

	@Autowired
	@Qualifier(value = "ataExcecaoService")
	private AtaExcecaoService excecaoService; 

	private final EventTrigger<EventListener> trigger;

	public AtaServiceImpl() {
		this.trigger = EventTrigger.newInstance(EventListener.class);
	}
	
	@Override
	public List<Ata> findAll() throws ServiceException {
		try {
			List<Ata> atas = new LinkedList<Ata>(this.repository.findAll());
			Collections.sort(atas);
			return atas;
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Ata findById(Long ataId, boolean loadLazyDependences) throws ServiceException {
		
		try {
			Ata ata = this.repository.findByIdentity(ataId);
			if(loadLazyDependences) {
				Hibernate.initialize(ata.getExceptions());
			}
			return ata;
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Ata> searchBy(AtaSearch search) throws ServiceException {
		try {

			List<Ata> atas = repository.searchBy(search);
			for(Ata ata : atas) {
				Hibernate.initialize(ata.getExceptions());
			}

			return atas;
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(Ata ata) throws ServiceException {

		try {

			Date today = DateUtils.getDate();

			AtaValidator validator = new AtaValidator(this.repository);
			ValidationResult validationResult = validator.validate(ata);

			if(!validationResult.isValid()) {
				throw new ValidationException(validationResult);
			}

			ata.setRecord(today);

			trigger.fire().onSave(new AtaEvent(ata));

			this.repository.save(ata);
			for(AtaExcecao excecao : ata.getExceptions()) {
				excecao.setAta(ata);
				this.excecaoService.save(excecao);
			}

		} catch (RepositoryException e) {
			throw new ServiceException(e);
		} catch (EventListenerException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(Ata ata) throws ServiceException {

		try {

			ValidationResult result = new ValidationResult();
			if(this.repository.hasTeamSeller(ata)) {
				result.addReason(new LocalizableReason("validation.ata.has.team.seller", ata.getName()));
				throw new ValidationException(result);
			}

			this.excecaoService.delete(ata.getExceptions());
			this.repository.delete(ata);

		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Listener<EventListener> getTrigger() {
		return this.trigger;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Ata findByRegistryDate(Date date) throws ServiceException {
		try {

			return this.repository.findByRegistryDate(date);

		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Ata findByMonthAndYear(Integer month, Integer year) throws ServiceException {
		try {

			if(month == 0) {
				month = 12;
				year = year - 1;
			}
			
			return this.repository.findByMonthAndYear(month, year);

		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

}
