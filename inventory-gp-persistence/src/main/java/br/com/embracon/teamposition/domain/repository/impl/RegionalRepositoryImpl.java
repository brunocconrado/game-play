package br.com.embracon.teamposition.domain.repository.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.embracon.teamposition.domain.entity.tmp.Regional;
import br.com.embracon.teamposition.domain.repository.RegionalRepository;


@Repository("regionalRepository")
@Transactional(propagation = Propagation.REQUIRED)
public class RegionalRepositoryImpl extends
		AbstractHibernateRepostirory<Regional> implements RegionalRepository {

	private static final long serialVersionUID = -1898490546797461132L;

	public RegionalRepositoryImpl() {
		super(Regional.class);
	}
	
	@Override
	public Regional findByCode(String code) {
		return findBy(Restrictions.eq("code", code));
	}


}
