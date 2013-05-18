package br.com.gp.inventory.domain.service.impl;

import java.util.List;

import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Manufacturer;
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

}
