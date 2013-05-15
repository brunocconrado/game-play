package br.com.gp.inventory.domain.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Branch;
import br.com.gp.inventory.domain.search.BranchSearch;
import br.com.gp.inventory.domain.service.BranchService;

@Controller("listBranchBean")
@Scope(value = "session")
public class ListBranchBean extends DefaultBean {

	@Autowired
	@Qualifier("branchService")
	private BranchService service;

	private BranchSearch branchSearch;

	private List<Branch> branches;
	
	private Branch branchSelected;
	
	private final static String BRANCH_LABEL = "Filial";

	public ListBranchBean() {
		super("listBranchBean");
		this.branchSearch = new BranchSearch();
	}

	public BranchService getService() {
		return service;
	}

	public void setService(BranchService service) {
		this.service = service;
	}

	public BranchSearch getBranchSearch() {
		return branchSearch;
	}

	public void setBranchSearch(BranchSearch branchSearch) {
		this.branchSearch = branchSearch;
	}

	public List<Branch> getBranches() {
		return branches;
	}

	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}

	public Branch getBranchSelected() {
		return branchSelected;
	}

	public void setBranchSelected(Branch branchSelected) {
		this.branchSelected = branchSelected;
	}

	public void search() {

		try {
			this.branches = this.service.searchBy(this.branchSearch);
		} catch (ServiceException e) {
			errorMessage("error.search", BRANCH_LABEL);
		} catch (Throwable e) {
			fatalMessage("fatal.error", e);
		}
	}
	
	public void remove() {
		
		try {
			
			this.service.remove(this.branchSelected);
			successMessage("remove.success", BRANCH_LABEL);
			
		} catch (ServiceException e) {
			errorMessage("error.remove", BRANCH_LABEL);
		} catch (Throwable e) {
			fatalMessage("fatal.error", e);
		}
	}
}
