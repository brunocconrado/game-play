package br.com.gp.inventory.domain.service;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Category;

public interface CategoryService {
	
	public Category findById(Long id) throws ServiceException;

}
