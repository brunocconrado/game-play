package br.com.gp.inventory.domain.bean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Tower;
import br.com.gp.inventory.domain.service.TowerService;


@Controller("towerListBean")
@Scope(value = "session")
public class TowerListBean extends DefaultBean {

	
	@Autowired
	@Qualifier("towerService")
	private TowerService service;
	
	private List<Tower> towers;
	
	private Tower towerSelected;
	
	public TowerListBean() {
		super("towerListBean");
	}
	
	@PostConstruct
	public void init() {
		try {
			this.towers = service.findAll();
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

	public List<Tower> getTowers() {
		return towers;
	}

	public void setTowers(List<Tower> towers) {
		this.towers = towers;
	}

	public Tower getTowerSelected() {
		return towerSelected;
	}

	public void setTowerSelected(Tower towerSelected) {
		this.towerSelected = towerSelected;
	}


}
