package br.com.gp.inventory.domain.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


@Controller("driveBean")
@Scope(value = "session")
public class DriveBean extends DefaultBean {

	public DriveBean() {
		super("driveBean");
	}

}
