package br.com.gp.inventory.domain.bean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Processor;
import br.com.gp.inventory.domain.service.ProcessorService;

@Controller("processorListBean")
@Scope(value = "session")
public class ProcessorListBean extends DefaultBean {

	@Autowired
	@Qualifier("processorService")
	private ProcessorService service;
	
	public ProcessorListBean() {
		super("processorListBean");
	}

	private List<Processor> processors;
	
	private Processor processorSelected;


	@PostConstruct
	public void init() {
		try {
			this.processors = service.findAll();
		} catch (ServiceException e) {
			fatalMessage("error.message.init", e);
			destroy("listProcessorBean");
		}
	}
	
	public void search() {
		init();
	}
	
	public void remove() {
		
	}
	
	public List<Processor> getProcessors() {
		return processors;
	}

	public void setProcessors(List<Processor> processors) {
		this.processors = processors;
	}

	public Processor getProcessorSelected() {
		return processorSelected;
	}

	public void setProcessorSelected(Processor processorSelected) {
		this.processorSelected = processorSelected;
	}
	
}
