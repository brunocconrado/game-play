package br.com.gp.inventory.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.embracon.j4e.domain.repository.RepositoryException;
import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Menu;
import br.com.gp.inventory.domain.repository.MenuRepository;
import br.com.gp.inventory.domain.service.MenuService;

@Component("menuService")
public class MenuServiceImpl implements MenuService {

	@Autowired
	@Qualifier(value = "menuRepository")
	private MenuRepository repository;

	public MenuServiceImpl() {}

	@Override
	public List<Menu> listAllActives() throws ServiceException {
		try {
			return repository.findAllActives();
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Menu findRootMenu() throws ServiceException {
		try {
			return repository.findRoot();
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

}
