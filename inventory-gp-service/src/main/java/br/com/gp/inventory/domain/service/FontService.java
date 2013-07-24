package br.com.gp.inventory.domain.service;

import java.util.List;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Drive;
import br.com.gp.inventory.domain.entity.Font;

public interface FontService {
	
	public void save(Font font) throws ServiceException;

	public List<Font> findAll() throws ServiceException;
	
	public void delete(Font font) throws ServiceException;

}
