package br.com.gp.inventory.domain.service;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Regional;

public interface RegionalService {
	
	public Regional findRegional(String getaRegionalCode) throws ServiceException;

	public void save(Regional getaRegional) throws ServiceException;

}
