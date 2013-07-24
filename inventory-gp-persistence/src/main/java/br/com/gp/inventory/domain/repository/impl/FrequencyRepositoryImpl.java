package br.com.gp.inventory.domain.repository.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.gp.inventory.domain.entity.Frequency;
import br.com.gp.inventory.domain.repository.FrequencyRepository;

@Repository("frequencyRepository")
@Transactional(propagation = Propagation.REQUIRED)
public class FrequencyRepositoryImpl extends AbstractHibernateRepostirory<Frequency> implements FrequencyRepository {

	private static final long serialVersionUID = 4526261785154672539L;

	public FrequencyRepositoryImpl() {
		super(Frequency.class);
	}

	@Override
	public Frequency findByName(String name) {
		return (Frequency) this.createCriteria()
				.add(Restrictions.eq("name", name)).uniqueResult();
	}

}
