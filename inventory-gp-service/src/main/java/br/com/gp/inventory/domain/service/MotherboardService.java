package br.com.gp.inventory.domain.service;

import java.util.List;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Drive;
import br.com.gp.inventory.domain.entity.Motherboard;

public interface MotherboardService {

	
	public List<Motherboard> findAll() throws ServiceException;
	
	public void save(Motherboard motherboard) throws ServiceException;
	
	public void delete(Motherboard motherboard) throws ServiceException;
	
}
