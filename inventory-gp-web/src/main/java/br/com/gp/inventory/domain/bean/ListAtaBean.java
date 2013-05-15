package br.com.gp.inventory.domain.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.embracon.j4e.validation.ValidationException;
import br.com.gp.inventory.domain.entity.Ata;
import br.com.gp.inventory.domain.search.AtaSearch;
import br.com.gp.inventory.domain.service.AtaService;


@Controller("listAtaBean")
@Scope(value = "session")
public class ListAtaBean extends DefaultBean {

	
	@Autowired
	@Qualifier("ataService")
	private AtaService service;
	
	private AtaSearch search;
	
	private Ata ataSelected;
	
	private List<Ata> atas;
	
	private final static String ATA_LABEL = "Ata";
	
	public ListAtaBean() {
		super("listAtaBean");
		this.search = new AtaSearch();
	}
	
	public AtaService getService() {
		return service;
	}

	public void setService(AtaService service) {
		this.service = service;
	}

	public AtaSearch getSearch() {
		return search;
	}

	public void setSearch(AtaSearch search) {
		this.search = search;
	}

	public Ata getAtaSelected() {
		return ataSelected;
	}

	public void setAtaSelected(Ata ataSelected) {
		this.ataSelected = ataSelected;
	}

	public List<Ata> getAtas() {
		return atas;
	}

	public void setAtas(List<Ata> atas) {
		this.atas = atas;
	}

	public void searchAtas() {
		
		try {
			this.atas = this.service.searchBy(this.search);
		} catch (ServiceException e) {
			errorMessage("error.search", ATA_LABEL);
		} catch (Throwable e) {
			fatalMessage("fatal.error", e);
		}
		
	}
	
	public void remove() {
		
		try {
			
			 this.service.remove(this.ataSelected);
			 this.atas.remove(this.ataSelected);
			 this.ataSelected = null;
			 
			 successMessage("remove.success", ATA_LABEL);
			 
		} catch (ValidationException e) {
			warningMessage(e.getResult().getReasons());
		} catch (ServiceException e) {
			errorMessage("error.remove", ATA_LABEL);
		} catch (Throwable e) {
			fatalMessage("fatal.error", e);
		}
	}
}
