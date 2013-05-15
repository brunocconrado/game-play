package br.com.embracon.teamposition.domain.service;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.embracon.teamposition.domain.entity.Administrative;

public interface AdministrativeService {

	public void save(Administrative administrative) throws ServiceException;

}
