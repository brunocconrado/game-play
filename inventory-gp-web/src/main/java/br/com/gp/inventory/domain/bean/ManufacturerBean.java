package br.com.gp.inventory.domain.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.gp.inventory.domain.service.ManufacturerService;


@Controller("manufacturerBean")
@Scope(value = "session")
public class ManufacturerBean extends DefaultBean {

	
	@Autowired
	@Qualifier("manufacturerService")
	private ManufacturerService service;
	
	public ManufacturerBean() {
		super("manufacturerBean");
	}

	
}
