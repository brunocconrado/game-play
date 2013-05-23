package br.com.gp.inventory.domain.service;

import java.util.List;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Drive;

public interface DriveService {
	
	public void save(Drive drive) throws ServiceException;

	public List<Drive> findAll() throws ServiceException;

}
