package br.com.gp.inventory.domain.service;

import java.util.List;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Manufacturer;

public interface ManufacturerService {
	
	public List<Manufacturer> findAll() throws ServiceException;

}
