package br.com.gp.inventory.domain.bean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Motherboard;
import br.com.gp.inventory.domain.service.MotherboardService;
import br.com.gp.inventory.domain.service.exception.AssociationViolationException;


@Controller("motherboardListBean")
@Scope(value = "session")
public class MotherboardListBean extends DefaultBean {

	@Autowired
	@Qualifier("motherboardService")
	private MotherboardService service;
	
	public MotherboardListBean() {
		super("motherboardListbean");
	}

	private List<Motherboard> motherboards;
	
	private Motherboard motherboardSelected;

	@PostConstruct
	public void init() {
		try {
			this.motherboards = service.findAll();
		} catch (ServiceException e) {
			fatalMessage("error.message.init", e);
			destroy("motherboardListBean");
		}
	}
	
	public void search() {
		init();
	}
	
	public void remove() {
		this.delete(this.service, "delete", 
				this.motherboardSelected, "Placa M‹e", "Invent‡rio");
	}

	public List<Motherboard> getMotherboards() {
		return motherboards;
	}

	public void setMotherboards(List<Motherboard> motherboards) {
		this.motherboards = motherboards;
	}

	public Motherboard getMotherboardSelected() {
		return motherboardSelected;
	}

	public void setMotherboardSelected(Motherboard motherboardSelected) {
		this.motherboardSelected = motherboardSelected;
	}
	
}
