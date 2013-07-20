package br.com.gp.inventory.domain.bean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Inventory;
import br.com.gp.inventory.domain.service.InventoryService;


@Controller("inventoryListBean")
@Scope(value = "session")
public class InventoryListBean extends DefaultBean {

	@Autowired
	@Qualifier("inventoryService")
	private InventoryService service;
	
	private List<Inventory> inventories;
	
	private Inventory inventorySelected;
	
	private Inventory inventoryReport;
	
	private String textHtml;

	public InventoryListBean() {
		super("inventoryListBean");
	}
	
	@PostConstruct
	public void init() {
		try {
			this.inventories = service.findAll();
		} catch (ServiceException e) {
			fatalMessage("error.message.init", e);
			destroy("motherboardListBean");
		}
	}
	
	public void search() {
		init();
	}
	
	public void remove() {
		
	}
	
	public void createHtmlText() {
		this.textHtml = this.service.createHtmlText(this.inventoryReport);				
	}

	public List<Inventory> getInventories() {
		return inventories;
	}

	public void setInventories(List<Inventory> inventories) {
		this.inventories = inventories;
	}

	public Inventory getInventorySelected() {
		return inventorySelected;
	}

	public void setInventorySelected(Inventory inventorySelected) {
		this.inventorySelected = inventorySelected;
	}

	public Inventory getInventoryReport() {
		return inventoryReport;
	}

	public void setInventoryReport(Inventory inventoryReport) {
		this.inventoryReport = inventoryReport;
		createHtmlText();
	}

	public String getTextHtml() {
		return textHtml;
	}

	public void setTextHtml(String textHtml) {
		this.textHtml = textHtml;
	}
	
	
	
}
