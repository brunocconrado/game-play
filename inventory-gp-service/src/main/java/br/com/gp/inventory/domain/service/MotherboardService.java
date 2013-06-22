package br.com.gp.inventory.domain.service;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Motherboard;

public interface MotherboardService {

	public List<Motherboard> findAll() throws ServiceException;
	
	public Motherboard save(Motherboard motherboard) throws ServiceException;

	public Motherboard findById(Long id) throws ServiceException;

	void importMotherboard(HSSFSheet sheet) throws ServiceException;
	
}
