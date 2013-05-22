package br.com.gp.inventory.domain.bean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Manufacturer;
import br.com.gp.inventory.domain.entity.Motherboard;
import br.com.gp.inventory.domain.entity.Socket;
import br.com.gp.inventory.domain.enumeration.CategoryEnum;
import br.com.gp.inventory.domain.service.ManufacturerService;
import br.com.gp.inventory.domain.service.MotherboardService;
import br.com.gp.inventory.domain.service.SocketService;

@Controller("motherboardBean")
@Scope(value = "session")
public class MotherboardBean extends DefaultBean {
	
	public MotherboardBean() {
		super("motherboardBean");
	}
	
	@Autowired
	@Qualifier("motherboardService")
	private MotherboardService service;
	
	@Autowired
	@Qualifier("manufacturerService")
	private ManufacturerService manufactoryService;
	
	@Autowired
	@Qualifier("socketService")
	private SocketService socketService;
	
	private List<Manufacturer> manufacturers;
	
	private List<Socket> sockets;
	
	private Motherboard motherboard;
	
	private Long manufacturerId;
	
	private Long socketId;
	
	@PostConstruct
	public void init() {
		try {
			
			this.motherboard = new Motherboard();
			
			this.manufacturers = manufactoryService.findAllByCategory(CategoryEnum.MOTHERBOARD);
			this.sockets = socketService.findAll();
			
		} catch (ServiceException e) {
			errorMessage("error.search", "Placa Mãe");
		}
	}
	
	public void save() {
		
		try {
			
			this.motherboard.setManufacturer(this.manufactoryService
							.findById(this.motherboard.getManufacturer().getId()));
			this.motherboard.setSocket(this.socketService
							.findById(this.motherboard.getSocket().getId()));
			
			this.service.save(this.motherboard);
			
			successMessage("save.success", "Placa Mãe");			
		} catch (ServiceException e) {
			errorMessage("error.search", "Placa Mãe");
		} catch (Throwable e) {
			fatalMessage("fatal.error");
		}
		
	}
	
	public String linkRedirect() {
		destroy("motherboardBean");
		return "/pages/placa-mae/lista";
	}
	
	public List<Manufacturer> getManufacturers() {
		return manufacturers;
	}

	public void setManufacturers(List<Manufacturer> manufacturers) {
		this.manufacturers = manufacturers;
	}
	
	public Long getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(Long manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public List<Socket> getSockets() {
		return sockets;
	}

	public void setSockets(List<Socket> sockets) {
		this.sockets = sockets;
	}

	public Motherboard getMotherboard() {
		return motherboard;
	}

	public void setMotherboard(Motherboard Motherboard) {
		this.motherboard = Motherboard;
	}

	public Long getSocketId() {
		return socketId;
	}

	public void setSocketId(Long socketId) {
		this.socketId = socketId;
	}
	 

}
