package br.com.embracon.teamposition.domain.service;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.embracon.teamposition.domain.entity.Regional;

public interface RegionalService {
	
	public Regional findRegional(String getaRegionalCode) throws ServiceException;

	public void save(Regional getaRegional) throws ServiceException;

}
