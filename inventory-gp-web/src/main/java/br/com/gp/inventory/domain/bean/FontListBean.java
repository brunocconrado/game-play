package br.com.gp.inventory.domain.bean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Font;
import br.com.gp.inventory.domain.service.FontService;
import br.com.gp.inventory.domain.service.exception.AssociationViolationException;

@Controller("fontListBean")
@Scope(value = "session")
public class FontListBean extends DefaultBean {
	
	
	@Autowired
	@Qualifier("fontService")
	private FontService service;
	
	public FontListBean() {
		super("fontListBean");
	}

	private List<Font> fonts;
	
	private Font fontSelected;

	@PostConstruct
	public void init() {
		try {
			this.fonts = service.findAll();
		} catch (ServiceException e) {
			fatalMessage("error.message.init", e);
			destroy("motherboardListBean");
		}
	}
	
	public void search() {
		init();
	}
	
	public void remove() {
		this.delete(this.service, "delete", 
				this.fontSelected, "Fonte", "Invent‡rio");
	}

	public List<Font> getFonts() {
		return fonts;
	}

	public void setFonts(List<Font> fonts) {
		this.fonts = fonts;
	}

	public Font getFontSelected() {
		return fontSelected;
	}

	public void setFontSelected(Font fontSelected) {
		this.fontSelected = fontSelected;
	}

}
