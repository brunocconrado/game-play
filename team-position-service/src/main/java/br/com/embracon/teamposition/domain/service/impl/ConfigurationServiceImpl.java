package br.com.embracon.teamposition.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.embracon.j4e.domain.repository.RepositoryException;
import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.embracon.teamposition.domain.entity.Configuration;
import br.com.embracon.teamposition.domain.enumeration.ConfigurationEnum;
import br.com.embracon.teamposition.domain.repository.ConfigurationRepository;
import br.com.embracon.teamposition.domain.service.ConfigurationService;

@Component("configurationService")
public class ConfigurationServiceImpl implements ConfigurationService {

	@Autowired
	@Qualifier("configurationRepository")
	private ConfigurationRepository repository;
	
	@Override
	public Configuration findByClazzAndType(Class<?> clazz, ConfigurationEnum config) throws ServiceException {
		try {
			return this.repository.findByClassAndType(clazz, config.value());
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

}
