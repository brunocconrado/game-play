package br.com.gp.inventory.domain.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Motherboard;
import br.com.gp.inventory.domain.service.MotherboardService;

@Component("motherboardConverter")
@FacesConverter(value="motherboardConverter", forClass=Motherboard.class) 
public class MotherboardConverter implements Converter {
	
	@Autowired
	@Qualifier("motherboardService")
	private MotherboardService motherboardService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,	String value) {
		
		try {
			List<Motherboard> all = this.motherboardService.findAll();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Estou no Converter!!!");
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return value.toString();
	}

}
