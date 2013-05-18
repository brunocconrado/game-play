package br.com.gp.inventory.domain.repository;

import br.com.embracon.j4e.domain.repository.Repository;
import br.com.gp.inventory.domain.entity.tmp.Configuration;

public interface ConfigurationRepository  extends Repository<Configuration> {
	
	public Configuration findByClassAndType(Class<?> clazz, String type);

}
