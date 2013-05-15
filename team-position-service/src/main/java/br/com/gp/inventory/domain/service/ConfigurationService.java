package br.com.gp.inventory.domain.service;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Configuration;
import br.com.gp.inventory.domain.enumeration.ConfigurationEnum;

public interface ConfigurationService {
	
	public Configuration findByClazzAndType(Class<?> clazz, ConfigurationEnum supervisorTeam) throws ServiceException;

}
