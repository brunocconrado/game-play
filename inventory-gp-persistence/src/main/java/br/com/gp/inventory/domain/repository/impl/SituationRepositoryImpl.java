package br.com.gp.inventory.domain.repository.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.embracon.j4e.domain.repository.RepositoryException;
import br.com.gp.inventory.domain.entity.tmp.Situation;
import br.com.gp.inventory.domain.repository.SituationRepository;

@Repository("situationRepository")
public class SituationRepositoryImpl extends AbstractHibernateRepostirory<Situation> implements SituationRepository {


	private static final long serialVersionUID = 3570454709128620859L;

	public SituationRepositoryImpl() {
		super(Situation.class);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Situation> findByType(Character seller) {
		
		try {
			return listBy(Restrictions.or(
						Restrictions.eq("type", Situation.BOTH), 
						Restrictions.eq("type", seller)));
		} catch (Exception e) {
			throw new RepositoryException(e);
		}
	}
}
