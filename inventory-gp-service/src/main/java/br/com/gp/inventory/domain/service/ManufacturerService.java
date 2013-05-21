package br.com.gp.inventory.domain.service;

import java.util.List;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Manufacturer;
import br.com.gp.inventory.domain.entity.Processor;
import br.com.gp.inventory.domain.enumeration.CategoryEnum;

public interface ManufacturerService {
	
	public List<Manufacturer> findAll() throws ServiceException;

	public void save(Processor processor) throws ServiceException;

	public Manufacturer findById(Long manufacturerId) throws ServiceException;

	public List<Manufacturer> findAllByCategory(CategoryEnum processor) throws ServiceException;

}
