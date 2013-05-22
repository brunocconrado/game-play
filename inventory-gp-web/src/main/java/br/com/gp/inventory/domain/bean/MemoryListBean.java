package br.com.gp.inventory.domain.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


@Controller("memoryListBean")
@Scope(value = "session")
public class MemoryListBean extends DefaultBean {

	public MemoryListBean() {
		super("memoryListBean");
	}

}
