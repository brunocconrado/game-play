package br.com.embracon.teamposition.domain.repository.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.embracon.teamposition.domain.entity.Collaborator;
import br.com.embracon.teamposition.domain.entity.TeamSeller;
import br.com.embracon.teamposition.domain.repository.CollaboratorRepository;

@Repository("collaboratorRepository")
public class CollaboratorRepositoryImpl  extends AbstractHibernateRepostirory<Collaborator> implements CollaboratorRepository {

	private static final long serialVersionUID = -5155055325563079423L;

	public CollaboratorRepositoryImpl() {
		super(Collaborator.class);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Collaborator findByRegistry(Integer registry) {
		return findBy(Restrictions.eq("registry", registry));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer countTeam(Collaborator supervisor) {
		Criteria criteria = getSession().createCriteria(TeamSeller.class);
		criteria = criteria.createCriteria("supervisor", "supervisor", JoinType.INNER_JOIN);
		
		criteria.add(Restrictions.eq("registry", supervisor.getRegistry()));
		criteria.setProjection(Projections.count("id"));
		
		return ((Long)criteria.uniqueResult()).intValue();
	}
}
