package br.com.embracon.teamposition.domain.repository.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import br.com.embracon.teamposition.domain.entity.tmp.Approvation;
import br.com.embracon.teamposition.domain.repository.ApprovationRepository;

@Repository("approvationRepository")
public class ApprovationRepositoryImpl extends AbstractHibernateRepostirory<Approvation> implements	ApprovationRepository {

	
	private static final long serialVersionUID = -6058664350273886214L;

	public ApprovationRepositoryImpl() {
		super(Approvation.class);
	}

	@Override
	public Approvation findByBranchAndAtaId(Long branchId, Long ataId) {
		
		Criteria criteria = createCriteria("approvation");
		criteria = criteria.createCriteria(
				"approvation.teamSellers", "teamSeller", JoinType.INNER_JOIN);
		
		criteria = criteria.createCriteria(
				"teamSeller.branch", "branch", JoinType.INNER_JOIN);
		
		criteria.add(Restrictions.eq("branch.id", branchId));
		
		criteria = criteria.createCriteria(
				"teamSeller.ata", "ata", JoinType.INNER_JOIN);
		
		criteria.add(Restrictions.eq("ata.id", ataId));
		
		return findBy(criteria);
		
	}

}