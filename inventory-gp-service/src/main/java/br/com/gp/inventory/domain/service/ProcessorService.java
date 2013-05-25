package br.com.gp.inventory.domain.service;

import java.util.List;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Motherboard;
import br.com.gp.inventory.domain.entity.Processor;

public interface ProcessorService {

	public List<Processor> findAll() throws ServiceException;

	public void save(Processor processor) throws ServiceException;

	public List<Processor> findByMotherboard(Motherboard motherboard) throws ServiceException;
	
}
