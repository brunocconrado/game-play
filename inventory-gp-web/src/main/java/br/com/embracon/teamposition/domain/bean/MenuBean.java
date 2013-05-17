package br.com.embracon.teamposition.domain.bean;

import javax.annotation.PostConstruct;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.embracon.j4e.i18n.Messages;
import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.embracon.teamposition.domain.entity.Menu;
import br.com.embracon.teamposition.domain.service.MenuService;
import br.com.embracon.teamposition.domain.vo.UserSession;
import br.com.embracon.teamposition.utils.TeamPositionProperties;

@Controller("menuBean")
@Scope(value = "globalSession")
public class MenuBean extends DefaultBean {
 
	private static final Long TEAM_SELLER_MENU = 5L;
	
	private static final Long REPOR_MENU = 6L;
	
	private static final Long TEAM_SELLER_SEARCH = 12L;

	private static final Long TEAM_SELLER_SAVE = 13L;
	
	private static final Long REPOR_MENU_SEARCH = 14L;
	
	@Autowired
	@Qualifier("menuService")
	private MenuService service;
	
	private MenuModel menuModel;

	public MenuBean() {
		super("menuBean");
		this.menuModel = new DefaultMenuModel();
	}

	public MenuModel getMenuModel() {
		return menuModel;
	}

	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}

	@PostConstruct
	public void init() {
		
		try {
			UserSession user = (UserSession) getFromSession(TeamPositionProperties.USER_LOGGED);
			if(user == null) {
				throw new IllegalArgumentException("no user found in session");
			}
			
			Menu root = service.findRootMenu();
			
			for (Menu menu : root.getChildren()) {
				Submenu submenu = createSubMenu(menu);
				for (Menu child : menu.getChildren()) {
					if(child.hasChildren()) {
						submenu.getChildren().add(createSubMenu(child));
					} else {
						submenu.getChildren().add(createMenuItem(child));
					}
				}
				menuModel.addSubmenu(submenu);
			}
		
		} catch (ServiceException e) {
			errorMessage("error.message.init", e, "Menu");
			destroy("menuBean");
		} catch (Exception e) {
			fatalMessage("error.message.init", e);
			destroy("menuBean");
		}
	}

	private Submenu createSubMenu(Menu menu) {
		Submenu submenu = new Submenu();
		submenu.setId("subMenu_" + menu.getId().intValue());
		submenu.setIcon(menu.getIcon());
		submenu.setLabel(Messages.getMessage(menu.getValue()));

		return submenu;
	}

	private MenuItem createMenuItem(Menu menu) {
		MenuItem menuItem = new MenuItem();
		menuItem.setId("menuItem_" + menu.getId());
		menuItem.setIcon(menu.getIcon());
		menuItem.setUrl(menu.getUrl());
		menuItem.setImmediate(true);
		menuItem.setValue(Messages.getMessage(menu.getValue()));
		menuItem.setAjax(true);

		return menuItem;
	}
}