package br.com.gp.inventory.domain.bean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.HardDisk;
import br.com.gp.inventory.domain.entity.Manufacturer;
import br.com.gp.inventory.domain.enumeration.CategoryEnum;
import br.com.gp.inventory.domain.service.HardDiskService;
import br.com.gp.inventory.domain.service.ManufacturerService;


@Controller("hardDiskBean")
@Scope(value = "session")
public class HardDiskBean extends DefaultBean {

	@Autowired
	@Qualifier("hardDiskService")
	private HardDiskService service;

	@Autowired
	@Qualifier("manufacturerService")
	private ManufacturerService manufacturerService;

	private HardDisk hardDisk;

	private List<Manufacturer> manufacturers;

	public HardDiskBean() {
		super("hardDiskBean");
	}
	
	@PostConstruct
	public void init() {
		try {
			
			this.hardDisk = new HardDisk();
			if(this.hardDisk == null) {
				
			}
			
			this.manufacturers = manufacturerService.findAllByCategory(CategoryEnum.HD);
			
		} catch (ServiceException e) {
			errorMessage("error.search", "HD/SSD");
		}
	}
	
	public void save() {
		
		try {
			
			this.hardDisk.setManufacturer(this.manufacturerService
							.findById(this.hardDisk.getManufacturer().getId()));

			this.service.save(this.hardDisk);
			
			this.hardDisk = new HardDisk();
			
			successMessage("save.success", "HD/SSD");			
		} catch (ServiceException e) {
			errorMessage("error.search", "HD/SSD");
		} catch (Throwable e) {
			fatalMessage("fatal.error");
		}
		
	}
	
	public String linkRedirect() {
		destroy("hardDiskBean");
		return "/pages/hd/lista";
	}
	
	public ManufacturerService getManufacturerService() {
		return manufacturerService;
	}

	public void setManufacturerService(ManufacturerService manufacturerService) {
		this.manufacturerService = manufacturerService;
	}

	public HardDisk getHardDisk() {
		return hardDisk;
	}

	public void setHardDisk(HardDisk hardDisk) {
		this.hardDisk = hardDisk;
	}

	public List<Manufacturer> getManufacturers() {
		return manufacturers;
	}

	public void setManufacturers(List<Manufacturer> manufacturers) {
		this.manufacturers = manufacturers;
	}

	
}
