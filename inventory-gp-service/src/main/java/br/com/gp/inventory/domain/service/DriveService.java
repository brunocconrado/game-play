package br.com.gp.inventory.domain.service;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Drive;

public interface DriveService {
	
	public Drive save(Drive drive) throws ServiceException;

	public List<Drive> findAll() throws ServiceException;

	public Drive findById(Long id) throws ServiceException;

	void importDriver(HSSFSheet sheet) throws ServiceException;

}
