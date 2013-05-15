package br.com.gp.inventory.domain.service;

import java.util.List;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Menu;

public interface MenuService {
	
	public List<Menu> listAllActives() throws ServiceException;

	public Menu findRootMenu() throws ServiceException;
 
}
