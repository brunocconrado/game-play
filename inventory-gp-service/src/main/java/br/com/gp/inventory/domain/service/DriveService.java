package br.com.gp.inventory.domain.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Drive;

public interface DriveService {
	
	public Drive save(Drive drive) throws ServiceException;

	public List<Drive> findAll() throws ServiceException;

<<<<<<< HEAD
	public void delete(Drive drive) throws ServiceException;
=======
	public Drive findById(Long id) throws ServiceException;

	void importDriver(Sheet sheet) throws ServiceException;
>>>>>>> ccdd4fa67d62621782d619c6692d92cf3cbe985b

}
