package br.com.gp.inventory.domain.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Drive;
import br.com.gp.inventory.domain.entity.Memory;

public interface MemoryService {

	public Memory save(Memory memory) throws ServiceException;

	public List<Memory> findAll() throws ServiceException;
	
	public void delete(Memory memory) throws ServiceException;

	public Memory findById(Long id) throws ServiceException;

	void importMemory(Sheet sheet) throws ServiceException;

}
