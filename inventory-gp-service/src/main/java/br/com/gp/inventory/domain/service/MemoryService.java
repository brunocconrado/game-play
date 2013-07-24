package br.com.gp.inventory.domain.service;

import java.util.List;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Drive;
import br.com.gp.inventory.domain.entity.Memory;

public interface MemoryService {

	public void save(Memory memory) throws ServiceException;

	public List<Memory> findAll() throws ServiceException;
	
	public void delete(Memory memory) throws ServiceException;

}
