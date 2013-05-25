package br.com.gp.inventory.domain.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.embracon.j4e.util.Objects;
import br.com.gp.inventory.domain.entity.Drive;
import br.com.gp.inventory.domain.entity.Font;
import br.com.gp.inventory.domain.entity.HardDisk;
import br.com.gp.inventory.domain.entity.Inventory;
import br.com.gp.inventory.domain.entity.Memory;
import br.com.gp.inventory.domain.entity.Motherboard;
import br.com.gp.inventory.domain.entity.Processor;
import br.com.gp.inventory.domain.entity.Tower;
import br.com.gp.inventory.domain.entity.VideoCard;
import br.com.gp.inventory.domain.service.DriveService;
import br.com.gp.inventory.domain.service.FontService;
import br.com.gp.inventory.domain.service.HardDiskService;
import br.com.gp.inventory.domain.service.InventoryService;
import br.com.gp.inventory.domain.service.MemoryService;
import br.com.gp.inventory.domain.service.MotherboardService;
import br.com.gp.inventory.domain.service.ProcessorService;
import br.com.gp.inventory.domain.service.TowerService;
import br.com.gp.inventory.domain.service.VideoCardService;


@Controller("inventoryBean")
@Scope(value = "session")
public class InventoryBean extends DefaultBean {

	@Autowired
	@Qualifier("inventoryService")
	private InventoryService service;
	
	@Autowired
	@Qualifier("motherboardService")
	private MotherboardService motherboardService;
	
	@Autowired
	@Qualifier("processorService")
	private ProcessorService processorService;
	
	@Autowired
	@Qualifier("memoryService")
	private MemoryService memoryService;
	
	@Autowired
	@Qualifier("hardDiskService")
	private HardDiskService hardDiskService;
	
	@Autowired
	@Qualifier("driveService")
	private DriveService driveService;
	
	@Autowired
	@Qualifier("videoCardService")
	private VideoCardService videoCardService;

	@Autowired
	@Qualifier("fontService")
	private FontService fontService;
	
	@Autowired
	@Qualifier("towerService")
	private TowerService towerService;

	private Inventory inventory;

	private List<Processor> processors;
	private List<Motherboard> motherboards;
	private List<Memory> memories;
	private List<HardDisk> hardDisks;
	private List<Drive> drivers;
	private List<VideoCard> videoCards;
	private List<Font> fonts;
	private List<Tower> towers;

	public InventoryBean() {
		super("inventoryBean");
	}
	
	@PostConstruct
	public void init() {
		try {
			
			if(this.inventory == null) {
				this.inventory = new Inventory();
			}
			
			this.motherboards = this.motherboardService.findAll();
			this.fonts = this.fontService.findAll();
			this.videoCards = this.videoCardService.findAll();
			this.memories = this.memoryService.findAll();
			this.drivers = this.driveService.findAll();
			this.towers = this.towerService.findAll();
			this.hardDisks = this.hardDiskService.findAll();
			
		} catch (ServiceException e) {
			errorMessage("error.search", "Inventário");
		}
	}
	
	public void save() {
		
		try {
			
			this.service.save(this.inventory);
			
			this.inventory = new Inventory();
			
			successMessage("save.success", "Inventário");			
		} catch (ServiceException e) {
			errorMessage("error.search", "Inventário");
		} catch (Throwable e) {
			fatalMessage("fatal.error");
		}
		
	}
	
	public void loadProcessors() {
		try {
			if(Objects.isNull(this.inventory.getMotherboard().getId())) {
				this.processors = new ArrayList<Processor>();
			} else {
				this.processors = this.processorService.findByMotherboard(this.inventory.getMotherboard());
			}
		} catch (ServiceException e) {
			errorMessage("error.search", "Processador");
		}
	}
	
	/*public List<Motherboard> autoCompleteMotherboard(String query) {

		List<Motherboard> found = new LinkedList<Motherboard>();
		for(Motherboard motherboard : this.motherboards) {
			if(motherboard.toString().contains(query)) {
				found.add(motherboard);
			}
		}
		return found;
	}*/

	public String linkRedirect() {
		destroy("inventoryBean");
		return "/pages/inventario/lista";
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public List<Processor> getProcessors() {
		return processors;
	}

	public void setProcessors(List<Processor> processors) {
		this.processors = processors;
	}

	public List<Motherboard> getMotherboards() {
		return motherboards;
	}

	public void setMotherboards(List<Motherboard> motherboards) {
		this.motherboards = motherboards;
	}

	public List<Memory> getMemories() {
		return memories;
	}

	public void setMemories(List<Memory> memories) {
		this.memories = memories;
	}

	public List<HardDisk> getHardDisks() {
		return hardDisks;
	}

	public void setHardDisks(List<HardDisk> hardDisks) {
		this.hardDisks = hardDisks;
	}

	public List<Drive> getDrivers() {
		return drivers;
	}

	public void setDrivers(List<Drive> drivers) {
		this.drivers = drivers;
	}

	public List<VideoCard> getVideoCards() {
		return videoCards;
	}

	public void setVideoCards(List<VideoCard> videoCards) {
		this.videoCards = videoCards;
	}

	public List<Font> getFonts() {
		return fonts;
	}

	public void setFonts(List<Font> fonts) {
		this.fonts = fonts;
	}

	public List<Tower> getTowers() {
		return towers;
	}

	public void setTowers(List<Tower> towers) {
		this.towers = towers;
	}
	
}
