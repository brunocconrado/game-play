package br.com.gp.inventory.domain.service;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.HardDisk;

public interface HardDiskService {

	public HardDisk save(HardDisk hardDisk) throws ServiceException;

	public List<HardDisk> findAll() throws ServiceException;

	public HardDisk findById(Long id) throws ServiceException;

	void importHardDisk(HSSFSheet sheet) throws ServiceException;

}
