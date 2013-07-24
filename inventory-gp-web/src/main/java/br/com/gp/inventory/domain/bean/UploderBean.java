package br.com.gp.inventory.domain.bean;

import java.io.IOException;

import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.gp.inventory.domain.service.XLSImporterService;

@Controller("uploderBean")
@Scope(value = "session")
public class UploderBean extends DefaultBean {

	public UploderBean() {
		super("uploderBean");
	}
	
	@Autowired(required = true)
	@Qualifier("xlsImporterService")
	private XLSImporterService xlsImporterService;
	
	public void doUpload(FileUploadEvent fileUploadEvent) { 
		
		try {
			xlsImporterService.importXLS(fileUploadEvent.getFile().getInputstream());
		} catch (IOException e) {
			errorMessage("error.search", "Importacao");
		}
	}
}
