package br.com.gp.inventory.domain.bean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Manufacturer;
import br.com.gp.inventory.domain.entity.Tower;
import br.com.gp.inventory.domain.enumeration.CategoryEnum;
import br.com.gp.inventory.domain.service.ManufacturerService;
import br.com.gp.inventory.domain.service.TowerService;


@Controller("towerBean")
@Scope(value = "session")
public class TowerBean extends DefaultBean {

	@Autowired
	@Qualifier("towerService")
	private TowerService service;
	
	@Autowired
	@Qualifier("manufacturerService")
	private ManufacturerService manufactoryService;
	
	private List<Manufacturer> manufacturers;
	
	private Tower tower;
	
	public TowerBean() {
		super("towerBean");
	}

	@PostConstruct
	public void init() {
		try {
			
			if(this.tower == null) {
				this.tower = new Tower();
			}
			
			this.manufacturers = manufactoryService.findAllByCategory(CategoryEnum.TOWER);
		} catch (ServiceException e) {
			errorMessage("error.search", "Gabinete");
		}
	}
	
	public void save() {
		
		try {
			
			this.tower.setManufacturer(this.manufactoryService
							.findById(this.tower.getManufacturer().getId()));
			
			this.service.save(this.tower);
			
			this.tower = new Tower();
			
			successMessage("save.success", "Gabinete");			
		} catch (ServiceException e) {
			errorMessage("error.search", "Gabinete");
		} catch (Throwable e) {
			fatalMessage("fatal.error");
		}
		
	}
	
	public String linkRedirect() {
		destroy("towerbean");
		return "/pages/gabinete/lista";
	}
	
	public List<Manufacturer> getManufacturers() {
		return manufacturers;
	}

	public void setManufacturers(List<Manufacturer> manufacturers) {
		this.manufacturers = manufacturers;
	}

	public Tower getTower() {
		return tower;
	}

	public void setTower(Tower tower) {
		this.tower = tower;
	}

}
