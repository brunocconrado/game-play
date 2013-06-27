package br.com.gp.inventory.domain.service.impl;

import java.util.List;

import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Manufacturer;
import br.com.gp.inventory.domain.enumeration.CategoryEnum;
import br.com.gp.inventory.domain.repository.ManufacturerRepository;
import br.com.gp.inventory.domain.service.CategoryService;
import br.com.gp.inventory.domain.service.ManufacturerService;

@Component("manufacturerService")
@Interceptors(value = {ServiceInteceptor.class})
public class ManufacturerServiceImpl implements ManufacturerService {

	@Autowired
	@Qualifier(value = "manufacturerRepository")
	private ManufacturerRepository repository;
	
	@Autowired
	@Qualifier(value = "categoryService")
	private CategoryService categortService;
	
	@Override
	public List<Manufacturer> findAll() throws ServiceException {
		return (List<Manufacturer>) repository.findAll();
	}

	@Override
	public Manufacturer findById(Long manufacturerId) throws ServiceException {
		return this.repository.findByIdentity(manufacturerId);
	}

	@Override
	public List<Manufacturer> findAllByCategory(CategoryEnum category) throws ServiceException {
		return (List<Manufacturer>) repository.findByCategory(category);
	}

	@Override
	public Manufacturer findByNameAndCategory(String name, CategoryEnum category) throws ServiceException {
		return this.repository.findByNameAndCategory(name.toUpperCase(), category);
	}

	@Override
	public Manufacturer save(Manufacturer manufacturer) throws ServiceException {
		return this.repository.save(manufacturer);
	}

	@Override
	public Manufacturer findOrCreateByNameAndCategory(String name, CategoryEnum category) throws ServiceException {
		Manufacturer manufacturer = this.findByNameAndCategory(name, category);
		if(manufacturer == null) {
			manufacturer = new Manufacturer(name.toUpperCase(), categortService.findById(category.value()));
			this.save(manufacturer);
		}
		
		return manufacturer;
	}

}
