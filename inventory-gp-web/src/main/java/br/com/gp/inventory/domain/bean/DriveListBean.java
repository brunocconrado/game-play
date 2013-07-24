package br.com.gp.inventory.domain.bean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Drive;
import br.com.gp.inventory.domain.service.DriveService;
import br.com.gp.inventory.domain.service.exception.AssociationViolationException;


@Controller("driveListBean")
@Scope(value = "session")
public class DriveListBean extends DefaultBean {

	@Autowired
	@Qualifier("driveService")
	private DriveService service;

	private List<Drive> drives;

	private Drive driveSelected;

	public DriveListBean() {
		super("driveBean");
	}

	@PostConstruct
	public void init() {
		try {
			this.drives = service.findAll();
		} catch (ServiceException e) {
			fatalMessage("error.message.init", e);
			destroy("driveListBean");
		}
	}

	public void search() {
		init();
	}

	public void remove() {
		this.delete(this.service, "delete", 
				this.driveSelected, "Drive", "Invent‡rio");
	}

	public List<Drive> getDrives() {
		return drives;
	}

	public void setDrives(List<Drive> drives) {
		this.drives = drives;
	}

	public Drive getDriveSelected() {
		return driveSelected;
	}

	public void setDriveSelected(Drive driveSelected) {
		this.driveSelected = driveSelected;
	}

}
