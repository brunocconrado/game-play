package br.com.embracon.teamposition.domain.service;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.embracon.teamposition.domain.entity.Configuration;
import br.com.embracon.teamposition.domain.enumeration.ConfigurationEnum;

public interface ConfigurationService {
	
	public Configuration findByClazzAndType(Class<?> clazz, ConfigurationEnum supervisorTeam) throws ServiceException;

}
