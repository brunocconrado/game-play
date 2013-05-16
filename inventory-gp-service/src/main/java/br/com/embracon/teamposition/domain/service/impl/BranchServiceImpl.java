package br.com.embracon.teamposition.domain.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.embracon.j4e.domain.repository.RepositoryException;
import br.com.embracon.j4e.reflection.ReflectionUtils;
import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.embracon.j4e.util.DateUtils;
import br.com.embracon.j4e.validation.LocalizableReason;
import br.com.embracon.j4e.validation.ValidationException;
import br.com.embracon.j4e.validation.ValidationResult;
import br.com.embracon.teamposition.domain.entity.Administrative;
import br.com.embracon.teamposition.domain.entity.Branch;
import br.com.embracon.teamposition.domain.entity.Collaborator;
import br.com.embracon.teamposition.domain.enumeration.StatusEnum;
import br.com.embracon.teamposition.domain.event.EventListener;
import br.com.embracon.teamposition.domain.event.EventTrigger;
import br.com.embracon.teamposition.domain.event.Listener;
import br.com.embracon.teamposition.domain.event.exception.EventListenerException;
import br.com.embracon.teamposition.domain.event.impl.BranchEvent;
import br.com.embracon.teamposition.domain.repository.BranchRepository;
import br.com.embracon.teamposition.domain.search.BranchSearch;
import br.com.embracon.teamposition.domain.service.AdministrativeService;
import br.com.embracon.teamposition.domain.service.BranchService;
import br.com.embracon.teamposition.domain.service.RegionalService;
import br.com.embracon.teamposition.domain.service.StatusService;
import br.com.embracon.teamposition.domain.validator.BranchValidator;
import br.com.embracon.teamposition.domain.vo.UserSession;
import br.com.embracon.wsentity.domain.entity.teamposition.BranchInfo;
import br.com.embracon.wsteamposition.ws.util.WSFactory;

@Component("branchService")
@SuppressWarnings({"unchecked", "rawtypes"})
public class BranchServiceImpl implements BranchService {

	@Autowired
	@Qualifier(value = "branchRepository")
	private BranchRepository repository;

	@Autowired
	@Qualifier(value = "regionalService")
	private RegionalService regionalService;

	@Autowired
	@Qualifier(value = "statusService")
	private StatusService statusService;
	
	@Autowired
	@Qualifier("administrativeService")
	private AdministrativeService administrativeService;

	private final EventTrigger<EventListener> trigger;

	public BranchServiceImpl() {
		this.trigger = EventTrigger.newInstance(EventListener.class);
	}


	@Override
	public List<Branch> findAllActive() {
		List<Branch> branches = 
				new LinkedList<Branch>(repository.findAllActive());
		Collections.sort(branches);
		return branches;
	}
	
	@Override
	public Branch findByCollaboratorRegistry(Integer registry) throws ServiceException {
		try {
			return repository.searchByCollaboratorRegistry(registry);
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Branch> searchBy(BranchSearch search) throws ServiceException {

		try {
			return repository.searchBy(search);
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Branch findById(Long branchIdSelected) {
		return repository.findByIdentity(branchIdSelected);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(Branch branch) throws ServiceException {

		try {

			branch.setStatus(this.statusService.findBy(StatusEnum.INACTIVE));

			this.repository.save(branch);

		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Branch findBranch(String code) throws ServiceException {

		try {

			br.com.embracon.wsembraconvo.teamposition.Branch branch = 
					new br.com.embracon.wsembraconvo.teamposition.Branch();
			branch.setCode(code);
			branch.setSystemCallIdentificator("009");

			BranchInfo result = WSFactory.getService().findBranch(branch);
			ValidationResult validationResult = new ValidationResult();
			if(result.getProcessStatus() == 99) {
				validationResult.addReason(new LocalizableReason(
						"validation.not.found", "Filial", "Codigo", 
						code == null ? " - " : code, "SCE"));
				throw new ValidationException(validationResult);
			}

			Branch found = repository.findByCode(code);
			if(found == null) {
				found = new Branch();
			}

			return ReflectionUtils.copySameFields(result, found);
		} catch (ValidationException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(Branch branch, UserSession editor) throws ServiceException {

		try {

			Date today = DateUtils.getDate();
			
			BranchValidator validator = new BranchValidator(this.repository);
			ValidationResult validationResult = validator.validate(branch);

			if(!validationResult.isValid()) {
				throw new ValidationException(validationResult);
			}

			if(branch.getId() == null) {
				branch.setStatus(statusService.findBy(StatusEnum.ACTIVE));
				branch.setRecord(today);
			}

			Collaborator manager = branch.getManager();
			if(manager.getRecord() == null) {
				manager.setRecord(today);
			}

			manager.setUpdate(today);

			Collaborator regional = branch.getRegional();
			if(regional.getRecord() == null) {
				regional.setRecord(today);
			}

			regional.setUpdate(today);
			
			this.regionalService.save(branch.getaRegional());

			trigger.fire().onSave(new BranchEvent(branch, editor));

			this.repository.save(branch);

		} catch (ValidationException e) {
			throw e;
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		} catch (EventListenerException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(Branch branch) throws ServiceException {

		try {

			if(branch.getAdministrative() != null) {
				Administrative administrative = branch.getAdministrative();
				administrative.setBranch(branch);
				this.administrativeService.save(administrative);
			}
			
			this.repository.save(branch);
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	public Listener<EventListener> getTrigger() {
		return this.trigger;
	}
}