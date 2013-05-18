package br.com.embracon.teamposition.domain.service;

import java.util.Collection;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.embracon.teamposition.domain.entity.Processor;

public interface ProcessorService {

	public Collection<Processor> findAll() throws ServiceException;
	
}
