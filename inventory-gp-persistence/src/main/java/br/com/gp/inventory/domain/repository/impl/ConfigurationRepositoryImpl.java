package br.com.gp.inventory.domain.repository.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.gp.inventory.domain.entity.tmp.Configuration;
import br.com.gp.inventory.domain.repository.ConfigurationRepository;

@Repository("configurationRepository")
@Transactional(propagation = Propagation.REQUIRED)
public class ConfigurationRepositoryImpl extends AbstractHibernateRepostirory<Configuration> implements
		ConfigurationRepository {

	private static final long serialVersionUID = 767079732865283110L;

	public ConfigurationRepositoryImpl() {
		super(Configuration.class);
	}
	
	public Configuration findByClassAndType(Class<?> clazz, String type) {
		
		return findBy(Restrictions.and(
				Restrictions.eq("className", clazz.getCanonicalName()),
				Restrictions.eq("type", type)));
	}


}
