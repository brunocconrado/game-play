package br.com.gp.inventory.domain.bean;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Manufacturer;
import br.com.gp.inventory.domain.entity.Processor;
import br.com.gp.inventory.domain.entity.Socket;
import br.com.gp.inventory.domain.enumeration.CategoryEnum;
import br.com.gp.inventory.domain.service.ManufacturerService;
import br.com.gp.inventory.domain.service.ProcessorService;
import br.com.gp.inventory.domain.service.SocketService;
import br.com.gp.inventory.domain.service.XLSImporterService;


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
	
	@Autowired(required = true)
	@Qualifier("xlsImporterService")
	private XLSImporterService xlsImporterService;
	
	private List<Manufacturer> manufacturers;
	
	private List<Socket> sockets;
	
	private Processor processor;
	
	public ProcessorBean() {
		super("processorBean");
	}

	@PostConstruct
	public void init() {
		try {
			
			if(this.processor == null) {
				this.processor = new Processor();
			}
			
			this.manufacturers = manufactoryService.findAllByCategory(CategoryEnum.PROCESSOR);
			this.sockets = socketService.findAll();
			
		} catch (ServiceException e) {
			fatalMessage("error.search", e, "Processador");
			destroy("processorBean");
		}
	}
	
	public void doUpload(FileUploadEvent fileUploadEvent) { 
		
		try {
			xlsImporterService.importXLS(fileUploadEvent.getFile().getInputstream());
			successMessage("Importação concluida com sucesso");
		} catch (IOException e) {
			errorMessage("error.search", e, "Importacao");
		}
	}
	
	public void save() {
		
		try {
			
			this.processor.setManufacturer(this.manufactoryService
							.findById(this.processor.getManufacturer().getId()));
			this.processor.setSocket(this.socketService
							.findById(this.processor.getSocket().getId()));
			
			this.service.save(this.processor);
			
			this.processor = new Processor();
			
			successMessage("save.success", "Processador");			
		} catch (ServiceException e) {
			errorMessage("error.search", e, "Processador");
		} catch (Throwable e) {
			fatalMessage("fatal.error", e);
		}
		
	}
	
	public void clear() {
		this.processor = new Processor();
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
}
