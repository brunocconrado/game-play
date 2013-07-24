package br.com.gp.inventory.domain.service;

import java.util.List;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Drive;
import br.com.gp.inventory.domain.entity.Tower;

public interface TowerService {
	
	public void save(Tower tower) throws ServiceException;

	public List<Tower> findAll() throws ServiceException;
	
	public void delete(Tower tower) throws ServiceException;

}
