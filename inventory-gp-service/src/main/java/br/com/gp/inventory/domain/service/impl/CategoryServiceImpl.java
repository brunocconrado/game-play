package br.com.gp.inventory.domain.service.impl;

import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Category;
import br.com.gp.inventory.domain.repository.CategoryRepository;
import br.com.gp.inventory.domain.service.CategoryService;


@Component("categoryService")
@Interceptors(value = {ServiceInteceptor.class})
public class CategoryServiceImpl implements CategoryService {
	
	
	@Autowired
	@Qualifier(value = "categoryRepository")
	private CategoryRepository repository;

	@Override
	public Category findById(Long id) throws ServiceException {
		return repository.findByIdentity(id);
	}

}
