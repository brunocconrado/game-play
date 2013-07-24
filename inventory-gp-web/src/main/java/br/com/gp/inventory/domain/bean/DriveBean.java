package br.com.gp.inventory.domain.bean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Drive;
import br.com.gp.inventory.domain.entity.Manufacturer;
import br.com.gp.inventory.domain.enumeration.CategoryEnum;
import br.com.gp.inventory.domain.service.DriveService;
import br.com.gp.inventory.domain.service.ManufacturerService;


@Controller("driveBean")
@Scope(value = "session")
public class DriveBean extends DefaultBean {

	@Autowired
	@Qualifier("driveService")
	private DriveService service;
	
	@Autowired
	@Qualifier("manufacturerService")
	private ManufacturerService manufactoryService;
	
	private List<Manufacturer> manufacturers;
	
	private Drive drive;
	
	public DriveBean() {
		super("driveBean");
	}

	@PostConstruct
	public void init() {
		try {
			
			if(this.drive == null) {
				this.drive = new Drive();
			}
			
			this.manufacturers = manufactoryService.findAllByCategory(CategoryEnum.DRIVE);
		} catch (ServiceException e) {
			fatalMessage("error.search", e, "Gabinete");
			destroy("driveBean");
		}
	}
	
	public void save() {
		
		try {
			
			this.drive.setManufacturer(this.manufactoryService
							.findById(this.drive.getManufacturer().getId()));
			
			this.service.save(this.drive);
			
			addCallbackParam("saved", true);
		} catch (ServiceException e) {
			errorMessage("error.search", e, "Gabinete");
		} catch (Throwable e) {
			fatalMessage("fatal.error", e);
		}
		
	}
	
	public void clear() {
		this.drive = new Drive();		
	}
	
	public String linkRedirect() {
		destroy("driveBean");
		return "/pages/drive/lista";
	}
	
	public List<Manufacturer> getManufacturers() {
		return manufacturers;
	}

	public void setManufacturers(List<Manufacturer> manufacturers) {
		this.manufacturers = manufacturers;
	}

	public Drive getDrive() {
		return drive;
	}

	public void setDrive(Drive drive) {
		this.drive = drive;
	}

}
