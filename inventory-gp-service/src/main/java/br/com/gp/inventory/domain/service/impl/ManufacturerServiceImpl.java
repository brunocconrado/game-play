package br.com.gp.inventory.domain.service.impl;

import java.util.List;

import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Manufacturer;
import br.com.gp.inventory.domain.entity.Processor;
import br.com.gp.inventory.domain.enumeration.CategoryEnum;
import br.com.gp.inventory.domain.repository.ManufacturerRepository;
import br.com.gp.inventory.domain.service.ManufacturerService;

@Component("manufacturerService")
@Interceptors(value = {ServiceInteceptor.class})
public class ManufacturerServiceImpl implements ManufacturerService {

	@Autowired
	@Qualifier(value = "manufacturerRepository")
	private ManufacturerRepository repository;
	
	@Override
	public List<Manufacturer> findAll() throws ServiceException {
		return (List<Manufacturer>) repository.findAll();
	}

	@Override
	public void save(Processor processor) throws ServiceException {
		// TODO Auto-generated method stub
		
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
	public Manufacturer findByName(String name) throws ServiceException {
		return null;
	}

	@Override
	public void save(Manufacturer manufacturer) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Manufacturer findOrCreateByName(String name) throws ServiceException {
		Manufacturer manufacturer = this.findByName(name);
		if(manufacturer == null) {
			manufacturer = new Manufacturer(name);
			this.save(manufacturer);
		}
		
		return manufacturer;
	}

}
