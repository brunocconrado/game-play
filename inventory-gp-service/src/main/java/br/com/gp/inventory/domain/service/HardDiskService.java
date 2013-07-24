package br.com.gp.inventory.domain.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Drive;
import br.com.gp.inventory.domain.entity.HardDisk;

public interface HardDiskService {

	public HardDisk save(HardDisk hardDisk) throws ServiceException;

	public List<HardDisk> findAll() throws ServiceException;
	
	public void delete(HardDisk hardDisk) throws ServiceException;

	public HardDisk findById(Long id) throws ServiceException;

	void importHardDisk(Sheet sheet) throws ServiceException;

}
