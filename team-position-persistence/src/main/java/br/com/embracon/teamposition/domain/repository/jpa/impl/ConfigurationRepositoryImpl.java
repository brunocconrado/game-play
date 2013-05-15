package br.com.embracon.teamposition.domain.repository.jpa.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import br.com.embracon.j4e.domain.repository.RepositoryException;
import br.com.embracon.teamposition.domain.entity.Configuration;
import br.com.embracon.teamposition.domain.repository.ConfigurationRepository;
import br.com.embracon.teamposition.domain.repository.jpa.AbstractPersistenceRepository;

@Stateless
@Local(ConfigurationRepository.class)
public class ConfigurationRepositoryImpl extends AbstractPersistenceRepository<Configuration> implements ConfigurationRepository {

	
	private static final long serialVersionUID = 2965345900094797380L;

	public ConfigurationRepositoryImpl() {
		super(Configuration.class);
	}

	@Override
	public Configuration findByClassAndType(Class<?> clazz, String type) {

		try {
			
			CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
			CriteriaQuery<Configuration> criteriaQuery = criteriaBuilder.createQuery(Configuration.class);
			Root<Configuration> config = criteriaQuery.from(Configuration.class);

			ParameterExpression<String> className = criteriaBuilder.parameter(String.class, "className");
			ParameterExpression<String> typeParam = criteriaBuilder.parameter(String.class, "type");

			criteriaQuery.where(criteriaBuilder.and(
					criteriaBuilder.equal(config.get("className"), className),
					criteriaBuilder.equal(config.get("type"), typeParam)
			));

			TypedQuery<Configuration> typedQuery = entityManager.createQuery(criteriaQuery);
			typedQuery.setParameter("className", clazz.getCanonicalName());
			typedQuery.setParameter("type", type);

			return typedQuery.getSingleResult();
			
		} catch (Exception e) {
			throw new RepositoryException(e);
		}
	}

}