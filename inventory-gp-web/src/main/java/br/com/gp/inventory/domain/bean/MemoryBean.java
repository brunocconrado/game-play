package br.com.gp.inventory.domain.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("memoryBean")
@Scope(value = "session")
public class MemoryBean extends DefaultBean {

	public MemoryBean() {
		super("driveBean");
	}

}
