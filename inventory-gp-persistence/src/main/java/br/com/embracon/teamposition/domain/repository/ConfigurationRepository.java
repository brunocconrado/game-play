package br.com.embracon.teamposition.domain.repository;

import br.com.embracon.j4e.domain.repository.Repository;
import br.com.embracon.teamposition.domain.entity.Configuration;

public interface ConfigurationRepository  extends Repository<Configuration> {
	
	public Configuration findByClassAndType(Class<?> clazz, String type);

}
