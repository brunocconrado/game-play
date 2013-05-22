package br.com.gp.inventory.domain.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


@Controller("driveListBean")
@Scope(value = "session")
public class DriveListBean extends DefaultBean {

	public DriveListBean() {
		super("driveBean");
	}

}
