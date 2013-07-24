package br.com.gp.inventory.domain.bean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Memory;
import br.com.gp.inventory.domain.service.MemoryService;
import br.com.gp.inventory.domain.service.exception.AssociationViolationException;


@Controller("memoryListBean")
@Scope(value = "session")
public class MemoryListBean extends DefaultBean {

	@Autowired
	@Qualifier("memoryService")
	private MemoryService service;
	
	public MemoryListBean() {
		super("memoryListBean");
	}

	private List<Memory> memories;
	
	private Memory memorySelected;

	@PostConstruct
	public void init() {
		try {
			this.memories = service.findAll();
		} catch (ServiceException e) {
			fatalMessage("error.message.init", e);
			destroy("memoryListBean");
		}
	}
	
	public void search() {
		init();
	}
	
	public void remove() {
		this.delete(this.service, "delete", 
				this.memorySelected, "Mem—ria", "Invent‡rio");
	}

	public List<Memory> getMemories() {
		return memories;
	}

	public void setMemories(List<Memory> memories) {
		this.memories = memories;
	}

	public Memory getMemorySelected() {
		return memorySelected;
	}

	public void setMemorySelected(Memory memorySelected) {
		this.memorySelected = memorySelected;
	}
	
}
