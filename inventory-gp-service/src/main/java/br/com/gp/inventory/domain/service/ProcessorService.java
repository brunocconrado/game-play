package br.com.gp.inventory.domain.service;

import java.util.Collection;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Processor;

public interface ProcessorService {

	public Collection<Processor> findAll() throws ServiceException;
	
}
