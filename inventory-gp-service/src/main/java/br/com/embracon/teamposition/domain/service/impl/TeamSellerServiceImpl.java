
package br.com.embracon.teamposition.domain.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.embracon.j4e.domain.repository.RepositoryException;
import br.com.embracon.j4e.i18n.Messages;
import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.embracon.j4e.util.DateUtils;
import br.com.embracon.j4e.util.Objects;
import br.com.embracon.j4e.validation.LocalizableReason;
import br.com.embracon.j4e.validation.ValidationException;
import br.com.embracon.j4e.validation.ValidationResult;
import br.com.embracon.teamposition.domain.entity.Approvation;
import br.com.embracon.teamposition.domain.entity.Ata;
import br.com.embracon.teamposition.domain.entity.AtaExcecao;
import br.com.embracon.teamposition.domain.entity.Branch;
import br.com.embracon.teamposition.domain.entity.Collaborator;
import br.com.embracon.teamposition.domain.entity.Status;
import br.com.embracon.teamposition.domain.entity.TeamSeller;
import br.com.embracon.teamposition.domain.enumeration.StatusEnum;
import br.com.embracon.teamposition.domain.repository.TeamSellerRepository;
import br.com.embracon.teamposition.domain.search.TeamSellerSearch;
import br.com.embracon.teamposition.domain.service.ApprovationService;
import br.com.embracon.teamposition.domain.service.AtaService;
import br.com.embracon.teamposition.domain.service.BranchService;
import br.com.embracon.teamposition.domain.service.StatusService;
import br.com.embracon.teamposition.domain.service.TeamSellerService;

@Component("teamSellerService")
public class TeamSellerServiceImpl implements TeamSellerService {
	
	
	private static final String TEAM_SELER = Messages.getMessage("label.team.position");

	@Autowired
	@Qualifier("teamSellerRepository")
	private TeamSellerRepository repository;

	@Autowired
	@Qualifier("branchService")
	private BranchService branchService;

	@Autowired
	@Qualifier("ataService")
	private AtaService ataService;

	@Autowired
	@Qualifier("statusService")
	private StatusService statusService;
	
	@Autowired
	@Qualifier("approvationService")
	private ApprovationService approvationService;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void processTeamSeller() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<TeamSeller> search(TeamSellerSearch tsSearch, StatusEnum... status) throws ServiceException {

		try {

			return this.repository.findBy(tsSearch, status);

		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(TeamSeller teamSeller) throws ServiceException {

		try {
			teamSeller.setStatus(this.statusService.findBy(StatusEnum.INACTIVE));
			this.repository.save(teamSeller);
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
		
	}
	
	public boolean validateRegistryPeriod() throws ServiceException {

		try {

			boolean isValid = Boolean.FALSE;

			Date today = DateUtils.getOnlyDate();

			Ata ata = this.ataService.findByRegistryDate(today);

			if(Objects.isNotNull(ata)) {

				isValid = ata.getRegistryStart().compareTo(today) <= 0 ||
						ata.getRegistryEnd().compareTo(today) >= 0;

				if(!isValid) {
					for(AtaExcecao excecao : ata.getExceptions()) {
						//TODO: Get user in session!!!
						/*Integer brachId = ConvertUtils.convert(
									Integer.class, excecao.getBranch().getCode());
						if(userLogget.getEstablishment().getId().equals(brachId)) {*/
						if(true) {
							isValid = excecao.getStart().compareTo(today) >= 0 ||
									excecao.getEnd().compareTo(today) <= 0;
							break;
						}
					}
				}
			}
			
			return isValid;

		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void existCollaboratorInOtherBranch(Collaborator collaborator, Long branchIdSelected)	throws ServiceException {

		try {
			Ata ata = this.ataService.findByRegistryDate(DateUtils.getDate());
			if(repository.findBy(branchIdSelected, ata.getId(), 
					collaborator.getRegistry(), StatusEnum.INACTIVE)) {
				ValidationResult result = new ValidationResult();
				result.addReason(new LocalizableReason(
							"validation.collaborator.other.branch", collaborator.getName()));
				throw new ValidationException(result);
			}

		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(TeamSeller teamSeller) throws ServiceException {

		try {

			ValidationResult result = new ValidationResult();
			if(!validateRegistryPeriod()) {
				result.addReason(new LocalizableReason("validation.expired.registry.date", "cadastro"));
				throw new ValidationException(result);
			}

			if(Objects.isNull(teamSeller.getSeller()) && 
					Objects.isNotNull(teamSeller.getSupervisor())) {
				result.addReason(new LocalizableReason("validation.supervisor.seller.required"));
			}
			
			Date today = DateUtils.getDate();
			teamSeller.setAta(this.ataService.findByRegistryDate(today));

			if (Objects.isNull(teamSeller.getRecord())) {
				teamSeller.setRecord(today);
			}

			teamSeller.setUpdate(today);
			teamSeller.setStatus(statusService.findBy(StatusEnum.PENDENT));

			this.branchService.update(teamSeller.getBranch());

			this.repository.save(teamSeller);
		} catch (ValidationException e) {
			throw e;
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public boolean isCloseByUser(Long branchId, Long ataId, StatusEnum... status) throws ServiceException {
		
		try {
			
			if(ataId == null) {
				ataId = this.ataService
							.findByRegistryDate(DateUtils.getOnlyDate()).getId();
			}

			return repository.hasTeamToAtaByBranchAndStatus(
					ataId, branchId, status);
					
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
		
	}		

	@Transactional(propagation = Propagation.REQUIRED)
	public void concludeRegistry(Long branchId, StatusEnum statusEnum) throws ServiceException {
		concludeRegistry(branchId, statusEnum, null, null, false);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void concludeRegistry(Long branchIdSelected, StatusEnum statusEnum, Collaborator approver, String message, boolean isAprovation) throws ServiceException {
		
		try {
			
			ValidationResult result = new ValidationResult();
			if(Objects.isNull(branchIdSelected)) {
				result.addReason(new LocalizableReason("validation.required", "Filial"));
				throw new ValidationException(result);
			}
			
			if(!validateRegistryPeriod()) {
				result.addReason(new LocalizableReason("validation.expired.registry.date", "cadastro"));
				throw new ValidationException(result);
			}
		
			Ata ata = this.ataService.findByRegistryDate(DateUtils.getOnlyDate());
			Branch branch = branchService.findById(branchIdSelected);
			
			StatusEnum[] statusList = new StatusEnum[isAprovation ? 1 : 2];
			if(isAprovation) {
				statusList[0] = StatusEnum.PENDENT_MANAGER;
			} else {
				statusList[0] = StatusEnum.PENDENT;
				statusList[1] = StatusEnum.REPROVED;
			}
			
			if(!isCloseByUser(branchIdSelected, ata.getId(), statusList)) {
				result.addReason(new LocalizableReason(
							"validation.team.seller.not.found", branch.getName()));
				throw new ValidationException(result);
			}
			
			List<TeamSeller> teamsSeller = this.repository.findByBranchAtaAndStatus(
						branchIdSelected, ata.getId(), statusList);
			
			if(teamsSeller.isEmpty()) {
				result.addReason(new LocalizableReason(
						"validation.not.found", "Equipe", 
						"Filial", branch.getName(), TEAM_SELER));
				throw new ValidationException(result);
			}
			
			Status status = statusService.findBy(statusEnum);
			Approvation approvation = null;
			if(isAprovation) {
				approvation = approvationService
						.findByBranchAndAta(branchIdSelected, ata.getId());
				approvation = approvation == null 
						? new Approvation() : approvation;
				approvation.setApprover(approver);
				approvation.setBranch(branch);
				approvation.setApprover(approver);
				approvation.setMessage(message);
				approvation.setStatus(status);
				approvationService.save(approvation);
			}
				
			for(TeamSeller teamSeller : teamsSeller) {
				teamSeller.setStatus(status);
				if(isAprovation) {
					teamSeller.setApprovation(approvation);
				}
				
				this.repository.save(teamsSeller);
			}
		} catch (ValidationException e) {
			throw e;
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

}
