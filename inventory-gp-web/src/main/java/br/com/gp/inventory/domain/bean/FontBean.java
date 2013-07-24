package br.com.gp.inventory.domain.bean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Drive;
import br.com.gp.inventory.domain.entity.Font;
import br.com.gp.inventory.domain.entity.Manufacturer;
import br.com.gp.inventory.domain.entity.Potential;
import br.com.gp.inventory.domain.enumeration.CategoryEnum;
import br.com.gp.inventory.domain.service.FontService;
import br.com.gp.inventory.domain.service.ManufacturerService;
import br.com.gp.inventory.domain.service.PotentialService;

@Controller("fontBean")
@Scope(value = "session")
public class FontBean extends DefaultBean {

	@Autowired
	@Qualifier("fontService")
	private FontService service;
	
	@Autowired
	@Qualifier("manufacturerService")
	private ManufacturerService manufactoryService;
	
	@Autowired
	@Qualifier("potentialService")
	private PotentialService potentialService;
	
	private List<Manufacturer> manufacturers;
	
	private List<Potential> potentials;
	
	private Font font;
	
	public FontBean() {
		super("fontBean");
	}
	
	@PostConstruct
	public void init() {
		try {
			this.font = new Font();
			
			this.manufacturers = manufactoryService.findAllByCategory(CategoryEnum.FONT);
			this.potentials = potentialService.findAll();
		} catch (ServiceException e) {
			fatalMessage("error.search", e, "Fonte");
			destroy("fontBean");
		}
	}
	
	public void save() {
		
		try {
			
			this.font.setManufacturer(this.manufactoryService
							.findById(this.font.getManufacturer().getId()));
			this.font.setPotential(this.potentialService
							.findById(this.font.getPotential().getId()));
			
			this.service.save(this.font);
			
			successMessage("save.success", "Fonte");			
		} catch (ServiceException e) {
			errorMessage("error.search", e, "Fonte");
		} catch (Throwable e) {
			fatalMessage("fatal.error", e);
		}
	}
	
	public void clear() {
		this.font = new Font();		
	}
	
	public String linkRedirect() {
		destroy("fontBean");
		return "/pages/fonte/lista";
	}
	
	public List<Manufacturer> getManufacturers() {
		return manufacturers;
	}

	public void setManufacturers(List<Manufacturer> manufacturers) {
		this.manufacturers = manufacturers;
	}

	public List<Potential> getPotentials() {
		return potentials;
	}

	public void setPotentials(List<Potential> potentials) {
		this.potentials = potentials;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}
	
}
