package br.com.gp.inventory.domain.bean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Manufacturer;
import br.com.gp.inventory.domain.entity.Processor;
import br.com.gp.inventory.domain.entity.Socket;
import br.com.gp.inventory.domain.service.ManufacturerService;
import br.com.gp.inventory.domain.service.ProcessorService;
import br.com.gp.inventory.domain.service.SocketService;


@Controller("processorBean")
@Scope(value = "session")
public class ProcessorBean extends DefaultBean {

	@Autowired
	@Qualifier("processorService")
	private ProcessorService service;
	
	@Autowired
	@Qualifier("manufacturerService")
	private ManufacturerService manufactoryService;
	
	@Autowired
	@Qualifier("socketService")
	private SocketService socketService;
	
	private List<Manufacturer> manufacturers;
	
	private List<Socket> sockets;
	
	private Processor processor;
	
	private Long manufacturerId;
	
	private Long socketId;
	
	public ProcessorBean() {
		super("processorBean");
	}

	@PostConstruct
	public void init() {
		try {
			
			this.processor = new Processor();
			
			this.manufacturers = manufactoryService.findAll();
			this.sockets = socketService.findAll();
			
		} catch (ServiceException e) {
			errorMessage("error.search", "Processador");
		}
	}
	
	public void save() {
		
	}
	
	public String linkRedirect() {
		destroy("processorBean");
		return "/pages/processador/lista";
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

	public Processor getProcessor() {
		return processor;
	}

	public void setProcessor(Processor processor) {
		this.processor = processor;
	}

	public Long getSocketId() {
		return socketId;
	}

	public void setSocketId(Long socketId) {
		this.socketId = socketId;
	}
	
	
	 
}
