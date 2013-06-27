package br.com.gp.inventory.domain.repository.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.gp.inventory.domain.entity.Potential;
import br.com.gp.inventory.domain.repository.PotentialRepository;

@Repository("potentialRepository")
@Transactional(propagation = Propagation.REQUIRED)
public class PotentialRepositoryImpl extends AbstractHibernateRepostirory<Potential> implements PotentialRepository {

	private static final long serialVersionUID = -3856218674346892403L;

	public PotentialRepositoryImpl() {
		super(Potential.class);
	}

	@Override
	public Potential findByName(String name) {
		return (Potential) this.createCriteria()
				.add(Restrictions.eq("name", name)).uniqueResult();
	}

}
