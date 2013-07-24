package br.com.gp.inventory.domain.bean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Frequency;
import br.com.gp.inventory.domain.entity.Manufacturer;
import br.com.gp.inventory.domain.entity.Memory;
import br.com.gp.inventory.domain.enumeration.CategoryEnum;
import br.com.gp.inventory.domain.service.FrequencyService;
import br.com.gp.inventory.domain.service.ManufacturerService;
import br.com.gp.inventory.domain.service.MemoryService;

@Controller("memoryBean")
@Scope(value = "session")
public class MemoryBean extends DefaultBean {

	@Autowired
	@Qualifier("memoryService")
	private MemoryService service;

	@Autowired
	@Qualifier("manufacturerService")
	private ManufacturerService manufacturerService;

	@Autowired
	@Qualifier("frequencyService")
	private FrequencyService frequencyService;

	private Memory memory;

	private List<Manufacturer> manufacturers;

	private List<Frequency> frequencies;

	public MemoryBean() {
		super("memoryBean");
	}

	@PostConstruct
	public void init() {
		try {
			
			this.memory = new Memory();
			if(this.memory == null) {
				
			}
			
			this.manufacturers = manufacturerService.findAllByCategory(CategoryEnum.MEMORY);
			this.frequencies = frequencyService.findAll();
			
		} catch (ServiceException e) {
			fatalMessage("error.search", e, "Mem—ria");
			destroy("memoryBean");
		}
	}
	
	public void save() {
		
		try {
			
			this.memory.setManufacturer(this.manufacturerService
							.findById(this.memory.getManufacturer().getId()));
			this.memory.setFrequency(this.frequencyService
							.findById(this.memory.getFrequency().getId()));
			
			this.service.save(this.memory);
			
			successMessage("save.success", "Mem—ria");			
		} catch (ServiceException e) {
			errorMessage("error.search", e, "Mem—ria");
		} catch (Throwable e) {
			fatalMessage("fatal.error", e);
		}
		
	}
	
	public void clear() {
		this.memory = new Memory();
	}
	
	public String linkRedirect() {
		destroy("memoryBean");
		return "/pages/memoria/lista";
	}
	
	public Memory getMemory() {
		return memory;
	}

	public void setMemory(Memory memory) {
		this.memory = memory;
	}

	public List<Manufacturer> getManufacturers() {
		return manufacturers;
	}

	public void setManufacturers(List<Manufacturer> manufacturers) {
		this.manufacturers = manufacturers;
	}

	public List<Frequency> getFrequencies() {
		return frequencies;
	}

	public void setFrequencies(List<Frequency> frequencies) {
		this.frequencies = frequencies;
	}
	
}
