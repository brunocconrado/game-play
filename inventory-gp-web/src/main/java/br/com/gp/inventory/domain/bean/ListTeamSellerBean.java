package br.com.gp.inventory.domain.bean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.embracon.j4e.i18n.Messages;
import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.embracon.j4e.validation.ValidationException;
import br.com.gp.inventory.domain.entity.Branch;
import br.com.gp.inventory.domain.entity.TeamSeller;
import br.com.gp.inventory.domain.enumeration.StatusEnum;
import br.com.gp.inventory.domain.search.TeamSellerSearch;
import br.com.gp.inventory.domain.service.BranchService;
import br.com.gp.inventory.domain.service.TeamSellerService;
import br.com.gp.inventory.domain.vo.UserSession;
import br.com.gp.inventory.utils.TeamPositionProperties;

@Controller("listTeamSellerBean")
@Scope(value = "session")
public class ListTeamSellerBean extends DefaultBean {


	private static final String TEAM_SELER = Messages.getMessage("label.team.position");


	@Autowired
	@Qualifier("teamSellerService") 
	private TeamSellerService service;

	@Autowired
	@Qualifier("branchService")
	private BranchService branchService;

	private TeamSellerSearch tsSearch;

	private TeamSeller teamSellerSelected;

	private TeamSeller teamSellerToDelete;

	private List<Branch> branches;

	private List<TeamSeller> teamSellers;

	private boolean concludedProcess;

	public TeamSellerSearch getTsSearch() {
		return tsSearch;
	}

	public void setTsSearch(TeamSellerSearch tsSearch) {
		this.tsSearch = tsSearch;
	}

	public TeamSeller getTeamSellerSelected() {
		return teamSellerSelected;
	}

	public void setTeamSellerSelected(TeamSeller teamSellerSelected) {
		this.teamSellerSelected = teamSellerSelected;
	}

	public TeamSeller getTeamSellerToDelete() {
		return teamSellerToDelete;
	}

	public void setTeamSellerToDelete(TeamSeller teamSellerToDelete) {
		this.teamSellerToDelete = teamSellerToDelete;
	}

	public List<Branch> getBranches() {
		return branches;
	}

	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}

	public List<TeamSeller> getTeamSellers() {
		return teamSellers;
	}

	public void setTeamSellers(List<TeamSeller> teamSellers) {
		this.teamSellers = teamSellers;
	}

	public boolean isConcludedProcess() {
		return concludedProcess;
	}

	public void setConcludedProcess(boolean concludedProcess) {
		this.concludedProcess = concludedProcess;
	}

	public ListTeamSellerBean() {
		super("listTeamSellerBean");
		this.tsSearch = new TeamSellerSearch();
		this.concludedProcess = Boolean.FALSE;
	}

	@PostConstruct
	public void init() {

		try {

			UserSession user =  (UserSession) 
					getFromSession(TeamPositionProperties.USER_LOGGED);

			this.branches =  this.branchService.findAllActive();
			this.concludedProcess = user.isCommercial() 
					|| this.service.validateRegistryPeriod();

		} catch (ServiceException e) {
			errorMessage("error.message.init", e, TEAM_SELER);
		} catch (Exception e) {
			fatalMessage("fatal.error", e);
		}
	}

	public void search() {

		try {

			this.teamSellers = this.service.search(this.tsSearch, 
					StatusEnum.PENDENT, StatusEnum.REPROVED);
			
			validateConcludeProcess();
		} catch (ServiceException e) {
			errorMessage("error.search", e, TEAM_SELER);
		} catch (Exception e) {
			fatalMessage("fatal.error", e);
		}
	}

	public void concludeRegistry() {
		try {

			this.service.concludeRegistry(
					this.tsSearch.getBranchId(), StatusEnum.PENDENT_MANAGER);
			
			this.search();
			
			successMessage("save.success", Messages.getMessage("lable.conclud"));
		} catch (ValidationException e) {
			warningMessage(e.getResult().getReasons());
		} catch (ServiceException e) {
			errorMessage("error.search", e, TEAM_SELER);
		} catch (Exception e) {
			fatalMessage("fatal.error", e);
		}
	}

	public void remove() {

		try {

			this.service.remove(this.teamSellerToDelete);
			this.teamSellers.remove(this.teamSellerToDelete);
			this.teamSellerToDelete = null;

		} catch (ServiceException e) {
			errorMessage("error.remove", e, TEAM_SELER);
		} catch (Exception e) {
			fatalMessage("fatal.error", e);
		}
	}

	public void closeTeamEdit() {
		this.concludedProcess = !this.concludedProcess;
	}

	public void cancelRemove() {
		this.teamSellerToDelete = null;
	}
	
	private void validateConcludeProcess() throws ServiceException {
		if(this.teamSellers == null || this.teamSellers.isEmpty()) {
			this.teamSellers = this.service.search(this.tsSearch, 
					StatusEnum.PENDENT_MANAGER);
			
			this.concludedProcess = this.teamSellers.isEmpty();
		} else {
			this.concludedProcess = this.service.validateRegistryPeriod() || 
					!this.service.isCloseByUser(this.tsSearch.getBranchId(), null, 
								StatusEnum.PENDENT_MANAGER);
		}
	}

}
