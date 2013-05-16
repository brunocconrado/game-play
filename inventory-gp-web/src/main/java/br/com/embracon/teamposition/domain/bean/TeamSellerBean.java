package br.com.embracon.teamposition.domain.bean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.embracon.j4e.i18n.Messages;
import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.embracon.j4e.util.Objects;
import br.com.embracon.j4e.validation.LocalizableReason;
import br.com.embracon.j4e.validation.ValidationException;
import br.com.embracon.j4e.validation.ValidationResult;
import br.com.embracon.teamposition.domain.entity.Administrative;
import br.com.embracon.teamposition.domain.entity.Branch;
import br.com.embracon.teamposition.domain.entity.Collaborator;
import br.com.embracon.teamposition.domain.entity.Situation;
import br.com.embracon.teamposition.domain.entity.TeamSeller;
import br.com.embracon.teamposition.domain.enumeration.SituationEnum;
import br.com.embracon.teamposition.domain.event.EventListener;
import br.com.embracon.teamposition.domain.search.CollaboratorSearch;
import br.com.embracon.teamposition.domain.service.ApprovationService;
import br.com.embracon.teamposition.domain.service.BranchService;
import br.com.embracon.teamposition.domain.service.ConfigurationService;
import br.com.embracon.teamposition.domain.service.SituationService;
import br.com.embracon.teamposition.domain.service.TeamSellerService;

@Controller("teamSellerBean")
@Scope(value = "session")
@SuppressWarnings("rawtypes")
public class TeamSellerBean extends DefaultBean {

	private static final String TEAM_SELER = Messages.getMessage("label.team.position");

	@Autowired
	@Qualifier("teamSellerService")
	private TeamSellerService service;

	@Autowired
	@Qualifier("branchService")
	private BranchService branchService;

	@Autowired
	@Qualifier("situationService")
	private SituationService situationService;

	@Autowired
	@Qualifier("configurationService")
	private ConfigurationService configurationService;
	
	@Autowired
	@Qualifier("approvationService")
	private ApprovationService approvationService;

	private TeamSeller teamSeller;

	private CollaboratorSearch sellerSearch;

	private CollaboratorSearch supervisorSearch;

	private CollaboratorSearch administrativeSearch;

	private Collaborator seller;
	
	private Branch branch;

	private Collaborator supervisor;

	private List<Branch> branches;

	private List<Situation> sellerSituations;

	private List<Situation> supervisorSituations;

	private List<Collaborator> administratives;

	private Long sellerSituationIdSelected;

	private Long supervisorSituationIdSelected;

	private Long branchIdSelected;

	private Long branchSellerTransferIdSelected;

	private Long branchSupervisorTransferIdSelected;

	private String administrativesString;

	private boolean sellerTransfer;

	private boolean supervisorTransfer;

	private boolean absenceSeller;

	private boolean absenceSupervisor;

	private boolean isNewTeamSeller;

	private boolean isInitialized;
	
	private int tabIndex;
	
	private EventListener approvationEventListener;

	public TeamSellerService getService() {
		return service;
	}

	public void setService(TeamSellerService service) {
		this.service = service;
	}

	public BranchService getBranchService() {
		return branchService;
	}

	public void setBranchService(BranchService branchService) {
		this.branchService = branchService;
	}

	public TeamSeller getTeamSeller() {
		return teamSeller;
	}

	public void setTeamSeller(TeamSeller teamSeller) {
		this.teamSeller = teamSeller;
		init();
	}

	public CollaboratorSearch getSellerSearch() {
		return sellerSearch;
	}

	public void setSellerSearch(CollaboratorSearch sellerSearch) {
		this.sellerSearch = sellerSearch;
	}

	public CollaboratorSearch getSupervisorSearch() {
		return supervisorSearch;
	}

	public void setSupervisorSearch(CollaboratorSearch supervisorSearch) {
		this.supervisorSearch = supervisorSearch;
	}

	public CollaboratorSearch getAdministrativeSearch() {
		return administrativeSearch;
	}

	public void setAdministrativeSearch(CollaboratorSearch administrativeSearch) {
		this.administrativeSearch = administrativeSearch;
	}

	public Collaborator getSeller() {
		return seller;
	}

	public void setSeller(Collaborator seller) {
		this.seller = seller;
	}

	public Collaborator getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Collaborator supervisor) {
		this.supervisor = supervisor;
	}

	public List<Collaborator> getAdministratives() {
		return administratives;
	}

	public void setAdministratives(List<Collaborator> administratives) {
		this.administratives = administratives;
	}

	public List<Branch> getBranches() {
		return branches;
	}

	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}

	public List<Situation> getSellerSituations() {
		return sellerSituations;
	}

	public void setSellerSituations(List<Situation> sellerSituations) {
		this.sellerSituations = sellerSituations;
	}

	public List<Situation> getSupervisorSituations() {
		return supervisorSituations;
	}

	public void setSupervisorSituations(List<Situation> supervisorSituations) {
		this.supervisorSituations = supervisorSituations;
	}

	public Long getSellerSituationIdSelected() {
		return sellerSituationIdSelected;
	}

	public void setSellerSituationIdSelected(Long sellerSituationIdSelected) {
		this.sellerSituationIdSelected = sellerSituationIdSelected;
	}

	public Long getSupervisorSituationIdSelected() {
		return supervisorSituationIdSelected;
	}

	public void setSupervisorSituationIdSelected(Long supervisorSituationIdSelected) {
		this.supervisorSituationIdSelected = supervisorSituationIdSelected;
	}

	public Long getBranchIdSelected() {
		return branchIdSelected;
	}

	public void setBranchIdSelected(Long branchIdSelected) {
		this.branchIdSelected = branchIdSelected;
	}

	public Long getBranchSellerTransferIdSelected() {
		return branchSellerTransferIdSelected;
	}

	public void setBranchSellerTransferIdSelected(
			Long branchSellerTransferIdSelected) {
		this.branchSellerTransferIdSelected = branchSellerTransferIdSelected;
	}

	public Long getBranchSupervisorTransferIdSelected() {
		return branchSupervisorTransferIdSelected;
	}

	public void setBranchSupervisorTransferIdSelected(
			Long branchSupervisorTransferIdSelected) {
		this.branchSupervisorTransferIdSelected = branchSupervisorTransferIdSelected;
	}

	public String getAdministrativesString() {
		return administrativesString;
	}

	public void setAdministrativesString(String administrativesString) {
		this.administrativesString = administrativesString;
	}

	public boolean isSellerTransfer() {
		return sellerTransfer;
	}

	public void setSellerTransfer(boolean sellerTransfer) {
		this.sellerTransfer = sellerTransfer;
	}

	public boolean isSupervisorTransfer() {
		return supervisorTransfer;
	}

	public void setSupervisorTransfer(boolean supervisorTransfer) {
		this.supervisorTransfer = supervisorTransfer;
	}

	public boolean isAbsenceSeller() {
		return absenceSeller;
	}

	public void setAbsenceSeller(boolean absenceSeller) {
		this.absenceSeller = absenceSeller;
	}

	public boolean isAbsenceSupervisor() {
		return absenceSupervisor;
	}

	public void setAbsenceSupervisor(boolean absenceSupervisor) {
		this.absenceSupervisor = absenceSupervisor;
	}

	public int getTabIndex() {
		return tabIndex;
	}

	public void setTabIndex(int tabIndex) {
		this.tabIndex = tabIndex;
	}
	
	public TeamSellerBean(@Qualifier("approvationListener")EventListener eventListener) {
		this();
		this.approvationEventListener = eventListener;
	}
	
	public TeamSellerBean() {
		super("teamSellerBean");
		this.teamSeller = new TeamSeller();
		this.sellerSearch = new CollaboratorSearch();
		this.supervisorSearch = new CollaboratorSearch();
		this.administrativeSearch = new CollaboratorSearch();
		this.supervisor = new Collaborator();
		this.seller = new Collaborator();
		this.administratives = new LinkedList<Collaborator>();
	}

	@PostConstruct
	public void init() {

		try {

			this.isNewTeamSeller = this.teamSeller.getId() == null;
			this.changeTab(0);

			this.approvationService.getTrigger()
					.addListener(this.approvationEventListener);
			
			if(!this.isInitialized) {
				this.branches = branchService.findAllActive();
				this.supervisorSituations = situationService.listSupervisorSituaions();
				this.sellerSituations = situationService.listSellerSituaions(); 
			}

			if(!this.isNewTeamSeller) {

				this.branch = this.teamSeller.getBranch();
				
				this.administratives = new LinkedList<Collaborator>(
						this.branch.getAdministrative().getColaborators());

				this.branchIdSelected = teamSeller.getBranch().getId();
				this.seller = this.teamSeller.getSeller();
				this.supervisor = this.teamSeller.getSupervisor();
				this.absenceSupervisor = this.teamSeller.isAbsenceSupervisor();

				this.supervisorSituationIdSelected = 
						this.supervisor.getSituation().getId();

				this.supervisorTransfer = this.teamSeller.isSupervisorTransfer();
				if(this.supervisorTransfer) {
					this.branchSupervisorTransferIdSelected = 
							this.teamSeller.getSupervisorTransfer().getId();
				}

				if(!this.teamSeller.isSupervisorFired()) {
					this.absenceSeller = this.teamSeller.isAbsenceSeller();		
					this.sellerSituationIdSelected = this.seller.getSituation().getId();

					this.sellerTransfer = this.teamSeller.isSellerTransfer();
					if(this.sellerTransfer) {
						this.branchSellerTransferIdSelected = 
								this.teamSeller.getSellerTransfer().getId();
					}
				}
			}

			this.isInitialized = Boolean.TRUE;
		} catch (ServiceException e) {
			errorMessage("error.message.init", e, TEAM_SELER);
		} catch (Exception e) {
			errorMessage("error.fatal", e);
		}
	}

	public void searchAdministrative() {


	}

	public void findSeller() {

	}

	public void findSupervisor() {

	}

	public void save() {

		try {

			boolean firstSave = this.teamSeller.getId() == null;
			
			if(this.branch == null) {
				ValidationResult result = new ValidationResult();
				result.addReason(new LocalizableReason("validation.required.fields"));
				throw new ValidationException(result);
			}
			
			if(Objects.isNull(this.branch.getAdministrative())) { 
				this.branch.setAdministrative(new Administrative());
			}
			
			this.branch.getAdministrative().getColaborators().addAll(this.administratives);
			this.teamSeller.setBranch(this.branch);
			
			if(!this.teamSeller.isSupervisorFired()) {
				this.seller.setSituation(this.situationService
						.findById(this.sellerSituationIdSelected));
				
				if(this.sellerTransfer) {
					this.teamSeller.setSellerTransfer(this.branchService
								.findById(this.branchSellerTransferIdSelected));
				} else {
					this.teamSeller.setSellerTransfer(null);
				}
				
				this.teamSeller.setSeller(this.seller);
			} else {
				this.seller = null;
			}

			if(!this.teamSeller.isManagerTeam()) {
				this.supervisor.setSituation(this.situationService
						.findById(this.supervisorSituationIdSelected));
				
				if(this.supervisorTransfer) {
					this.teamSeller.setSupervisorTransfer(this.branchService
								.findById(this.branchSupervisorTransferIdSelected));
				} else {
					this.teamSeller.setSupervisorTransfer(null);
				}
				
				this.teamSeller.setSupervisor(this.supervisor);
			} else {
				this.supervisor = null;
			}
			
			this.service.save(this.teamSeller);
			
			successMessage("save.success", "Equipe");
			
			addCallbackParam("firstSave", firstSave);
		} catch (ValidationException e) {
			warningMessage(e.getResult().getReasons());
			realese();
		}	catch (ServiceException e) {
			errorMessage("error.save", e, "Equipe");
			realese();
		} catch (Exception e) {
			fatalMessage("fatal.error", e);
			realese();
		}
	}
	
	public void findAdministratives() {
		
		try {
			
			this.branch = this.branchService.findById(this.branchIdSelected);
			if(this.branch.getAdministrative() != null) {
				this.administratives = new ArrayList<Collaborator>(
						this.branch.getAdministrative().getColaborators());
			} else {
				this.administratives = new ArrayList<Collaborator>();
			}
			administrativesString();
		} catch (ServiceException e) {
			errorMessage("error.search", e, "Administrativa");
		} catch (Exception e) {
			fatalMessage("fatal.error", e);
		}
	}

	public void validateSituation(int index) {
		
		this.changeTab(index);

		final Long TRANSFER = SituationEnum.TRANFER.id();
		this.supervisorTransfer = TRANSFER
					.equals(this.supervisorSituationIdSelected);

		this.sellerTransfer =  TRANSFER
					.equals(this.sellerSituationIdSelected);

		if(this.teamSeller.isSupervisorFired()) {
			this.supervisorSituationIdSelected = SituationEnum.FIRED.id();
			this.teamSeller.setSupervisorFired(true);
		}
		
		final Long RETIRED_ID = SituationEnum.ABSENCE.id();
		if(RETIRED_ID.equals(this.sellerSituationIdSelected)) {
			this.teamSeller.setManagerTeam(true);
			this.absenceSeller = this.seller.getAbsenceStart() != null 
					|| this.seller.getAbsenceEnd() != null;
			if(!this.absenceSeller) {
				this.teamSeller.setManagerTeam(false);
				this.sellerSituationIdSelected = null;
				warningMessage("validation.required.absence.date", 
							this.seller.getName().toUpperCase(), "");
			}
		}
		
		if(RETIRED_ID.equals(this.supervisorSituationIdSelected)) {
			this.absenceSupervisor = this.supervisor.getAbsenceStart() != null 
					&& this.supervisor.getAbsenceEnd() != null;
			if(!this.absenceSupervisor) {
				this.supervisorSituationIdSelected = null;
				warningMessage("validation.required.absence.date", 
						this.seller.getName().toUpperCase(), "");
			}
		}
	}

	public void removeAdministrative(Collaborator adm) {
		this.administratives.remove(adm);
	}

	public void renewAdmSearch() {
		this.administrativeSearch = new CollaboratorSearch();
	}

	public String administrativesString() {
		this.administrativesString = "";
		if(!this.administratives.isEmpty()) {
			for(Collaborator c : this.administratives) {
				administrativesString +=  c.getName() + "; ";
			}
		}

		return administrativesString.length() >= 33 ? 
				administrativesString.substring(0, 33) + "..." : administrativesString;
	}

	public String linkRedirect() {
		clear();
		return "/pages/equipe-vendedor/lista";
	}
	
	public void changeTab(int index) {
		this.tabIndex = index;
	}
	
	public void supervisorIsFired() {
		this.supervisorSituationIdSelected = SituationEnum.FIRED.id();
	}
	
	public void clear() {
		this.teamSeller = new TeamSeller();
		this.sellerSearch = new CollaboratorSearch();
		this.supervisorSearch = new CollaboratorSearch();
		this.administrativeSearch = new CollaboratorSearch();
		this.seller = new Collaborator();
		this.sellerSituationIdSelected = null;
		this.branchSellerTransferIdSelected = null;
		this.sellerTransfer = false;
		this.supervisorTransfer = false;
		this.absenceSeller = false;
		this.absenceSupervisor = false;
		this.isNewTeamSeller = true;
		this.isInitialized = false;
		this.tabIndex = 0;
	}
	
	private void realese() {
		if(isNewTeamSeller) {
			this.teamSeller.setId(null);
		}
	}
}
