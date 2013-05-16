package br.com.embracon.teamposition.domain.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.embracon.j4e.i18n.Messages;
import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.embracon.j4e.util.Objects;
import br.com.embracon.j4e.validation.ValidationException;
import br.com.embracon.teamposition.domain.entity.Branch;
import br.com.embracon.teamposition.domain.entity.Collaborator;
import br.com.embracon.teamposition.domain.entity.Regional;
import br.com.embracon.teamposition.domain.event.EventListener;
import br.com.embracon.teamposition.domain.search.BranchSearch;
import br.com.embracon.teamposition.domain.service.BranchService;
import br.com.embracon.teamposition.domain.service.RegionalService;
import br.com.embracon.teamposition.domain.vo.UserSession;
import br.com.embracon.teamposition.utils.FacesUtils;
import br.com.embracon.teamposition.utils.TeamPositionProperties;

@Controller("branchBean")
@Scope(value = "session")
@SuppressWarnings("rawtypes")
public class BranchBean extends DefaultBean {

	@Autowired
	@Qualifier("branchService")
	private BranchService service;

	@Autowired
	@Qualifier("regionalService")
	private RegionalService regionalService;

	private Branch branch;

	private Regional aRegional;

	private Collaborator manager;

	private Collaborator regional;
	
	private List<Collaborator> result;

	private BranchSearch branchSearch;

	private EventListener branchEventListener;
	
	private String collaboratorType;
	
	private String typePerson = "F";
	
	private String searchTitle;

	private boolean disabledCenterValue = Boolean.TRUE;

	private boolean disabledName = Boolean.TRUE;

	private boolean disabledARegionalName = Boolean.TRUE;

	private boolean disabledDirCode = Boolean.TRUE;

	private boolean disabledRegionalName = Boolean.TRUE;

	private boolean disabledManagerName = Boolean.TRUE;

	private boolean disabledManagerCode = Boolean.TRUE;

	private boolean disabledBranchCode = Boolean.TRUE;

	private boolean disabledARegionalCode = Boolean.TRUE;

	private boolean isNewBranch;
	
	@Autowired
	public BranchBean(@Qualifier("branchListener")EventListener eventListener) {
		this();
		this.branchEventListener = eventListener;
	}

	public BranchBean() {
		super("branchBean");
		this.branchSearch = new BranchSearch();
		this.result = new ArrayList<Collaborator>();
	}

	@PostConstruct
	public void init() {

		this.service.getTrigger().addListener(this.branchEventListener);

		if(this.branch == null) {
			this.branch = new Branch();
		}

		this.isNewBranch = this.branch.getId() == null;
		if(!this.isNewBranch) {
			this.aRegional = branch.getaRegional();
			this.manager = branch.getManager();
			this.regional = branch.getRegional();

			verifyRegionalProperties();
			verifyManagerProperties();
		}
	}

	public BranchService getService() {
		return service;
	}

	public void setService(BranchService service) {
		this.service = service;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
		init();
	}

	public Regional getaRegional() {
		return aRegional;
	}

	public void setaRegional(Regional aRegional) {
		this.aRegional = aRegional;
	}

	public Collaborator getManager() {
		return manager;
	}

	public void setManager(Collaborator manager) {
		this.manager = manager;
	}

	public Collaborator getRegional() {
		return regional;
	}

	public void setRegional(Collaborator regional) {
		this.regional = regional;
	}

	public List<Collaborator> getResult() {
		return result;
	}

	public void setResult(List<Collaborator> result) {
		this.result = result;
	}

	public BranchSearch getBranchSearch() {
		return branchSearch;
	}

	public void setBranchSearch(BranchSearch branchSearch) {
		this.branchSearch = branchSearch;
	}

	public String getCollaboratorType() {
		return collaboratorType;
	}

	public void setCollaboratorType(String collaboratorType) {
		this.collaboratorType = collaboratorType;
	}

	public String getSearchTitle() {
		return searchTitle;
	}

	public void setSearchTitle(String searchTitle) {
		this.searchTitle = searchTitle;
	}

	public String getTypePerson() {
		return typePerson;
	}

	public void setTypePerson(String typePerson) {
		this.typePerson = typePerson;
	}

	public boolean isDisabledCenterValue() {
		return disabledCenterValue;
	}

	public void setDisabledCenterValue(boolean disabledCenterValue) {
		this.disabledCenterValue = disabledCenterValue;
	}

	public boolean isDisabledName() {
		return disabledName;
	}

	public void setDisabledName(boolean disabledName) {
		this.disabledName = disabledName;
	}

	public boolean isDisabledARegionalName() {
		return disabledARegionalName;
	}

	public void setDisabledARegionalName(boolean disabledARegionalName) {
		this.disabledARegionalName = disabledARegionalName;
	}

	public boolean isDisabledDirCode() {
		return disabledDirCode;
	}

	public void setDisabledDirCode(boolean disabledDirCode) {
		this.disabledDirCode = disabledDirCode;
	}

	public boolean isDisabledRegionalName() {
		return disabledRegionalName;
	}

	public void setDisabledRegionalName(boolean disabledRegionalName) {
		this.disabledRegionalName = disabledRegionalName;
	}

	public boolean isDisabledManagerName() {
		return disabledManagerName;
	}

	public void setDisabledManagerName(boolean disabledManagerName) {
		this.disabledManagerName = disabledManagerName;
	}

	public boolean isDisabledManagerCode() {
		return disabledManagerCode;
	}

	public void setDisabledManagerCode(boolean disabledManagerCode) {
		this.disabledManagerCode = disabledManagerCode;
	}

	public boolean isDisabledBranchCode() {
		return disabledBranchCode;
	}

	public void setDisabledBranchCode(boolean disabledBranchCode) {
		this.disabledBranchCode = disabledBranchCode;
	}

	public boolean isDisabledARegionalCode() {
		return disabledARegionalCode;
	}

	public void setDisabledARegionalCode(boolean disabledARegionalCode) {
		this.disabledARegionalCode = disabledARegionalCode;
	}

	public void searchType(String type) { 
		this.collaboratorType = type;
		this.searchTitle = Messages.getMessage("data.table.search.manager.regional",  
					Messages.getMessage(type.equals("M") ? "label.manager" : "label.regional"));
		
		this.branchSearch = new BranchSearch();
	}
	
	public void loadBranch() {

		try {

			Long id = this.branch.getId();
			this.branch = this.service.findBranch(this.branchSearch.getCode());
			if(!isNewBranch) {
				this.branch.setId(id);
			}

		} catch (ValidationException e) {
			warningMessage(e.getResult().getReasons());
		} catch (ServiceException e) {
			errorMessage("error.search", "Filial");
		} catch (Exception e) {
			fatalMessage("fatal.error");
		}  
	}

	public void loadARegional() {

		try {

			this.aRegional = this.regionalService
					.findRegional(this.branchSearch.getaRegionalCode());

		} catch (ValidationException e) {
			warningMessage(e.getResult().getReasons());
		} catch (ServiceException e) {
			errorMessage("error.search", "Regional");
			e.printStackTrace();
		} catch (Exception e) {
			fatalMessage("fatal.error");
			e.printStackTrace();
		}  
	}
	
	public void loadManager() {

		try {

			this.result.clear();
			
			if(Objects.isNotNull(this.manager)) {
				this.result.add(this.manager);
			}

			verifyManagerProperties();
		} catch (ValidationException e) {
			warningMessage(e.getResult().getReasons());
		} catch (Exception e) {
			fatalMessage("fatal.error");
		} 
	}

	public void loadRegional() {

		try {

			this.result.clear();
			
			
			if(Objects.isNotNull(this.regional)) {
				this.result.add(this.regional);
			} 

			verifyRegionalProperties();
		} catch (ValidationException e) {
			warningMessage(e.getResult().getReasons());
		} catch (Exception e) {
			fatalMessage("fatal.error");
		}  
	}
	
	public void loadCollaborator() {
		if(this.collaboratorType.equals("M")) {
			loadManager();
		} else {
			loadRegional();
		}
	}

	public void save() {

		try {

			boolean firstSave = this.branch.getId() == null;

			this.branch.setaRegional(this.aRegional);
			this.branch.setRegional(this.regional);
			
			if(branch.isWithoutmanager()) {
				branch.setManager(this.regional);
			} else {
				this.branch.setManager(this.manager);
			}

			this.service.save(this.branch, (UserSession) 
						getFromSession(TeamPositionProperties.USER_LOGGED));

			successMessage("save.success", "Filial");

			FacesUtils.addCallbackParam("firstSave", firstSave);
		} catch (ValidationException e) {
			warningMessage(e.getResult().getReasons());
		} catch (ServiceException e) {
			errorMessage("error.save", "Filial");
			invalidateId();
		} catch (Exception e) {
			fatalMessage("fatal.error");
			invalidateId();
		}
	}

	public void clear() {
		destroy("branchBean");
	}

	public String linkRedirect() {
		destroy("branchBean");
		return "/pages/filial/lista";
	}

	private void verifyManagerProperties() {
		this.disabledManagerCode = this.manager != null && 
				!(this.manager.getCode() == null || this.manager.getCode().isEmpty());
	}

	private void verifyRegionalProperties() {
		this.disabledDirCode = this.regional != null &&  
				!(this.regional.getCode() == null || this.regional.getCode().isEmpty() || 
						(this.regional.getRegistry() != null && (
								this.regional.getRegistry() < 0 || 
										this.regional.getRegistry().equals(34909))));
	}

	private void invalidateId() {
		if(this.isNewBranch) {
			this.branch.setId(null);
		}
	}
}
