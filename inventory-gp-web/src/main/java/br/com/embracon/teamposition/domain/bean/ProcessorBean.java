package br.com.embracon.teamposition.domain.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


@Controller("processorBean")
@Scope(value = "globalSession")
public class ProcessorBean extends DefaultBean {

	private Process processor;
	
	public ProcessorBean() {
		super("processorBean");
	}
	
}
