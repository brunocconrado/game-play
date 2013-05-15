package br.com.gp.inventory.domain.service;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Administrative;

public interface AdministrativeService {

	public void save(Administrative administrative) throws ServiceException;

}
