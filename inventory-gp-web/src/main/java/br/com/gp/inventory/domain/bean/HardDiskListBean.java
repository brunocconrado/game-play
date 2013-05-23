package br.com.gp.inventory.domain.bean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.HardDisk;
import br.com.gp.inventory.domain.service.HardDiskService;

@Controller("hardDiskListBean")
@Scope(value = "session")
public class HardDiskListBean extends DefaultBean {

	@Autowired
	@Qualifier("hardDiskService")
	private HardDiskService service;

	private List<HardDisk> hardDisks;

	private HardDisk hardDiskSelected;

	public HardDiskListBean() {
		super("hardDiskRepository");
	}
	
	@PostConstruct
	public void init() {
		try {
			this.hardDisks = service.findAll();
		} catch (ServiceException e) {
			fatalMessage("error.message.init", e);
			destroy("hardDiskListBean");
		}
	}

	public void search() {
		init();
	}

	public void remove() {

	}

	public List<HardDisk> getHardDisks() {
		return hardDisks;
	}

	public void setHardDisks(List<HardDisk> hardDisks) {
		this.hardDisks = hardDisks;
	}

	public HardDisk getHardDiskSelected() {
		return hardDiskSelected;
	}

	public void setHardDiskSelected(HardDisk hardDiskSelected) {
		this.hardDiskSelected = hardDiskSelected;
	}

}
