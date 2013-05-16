package br.com.embracon.teamposition.domain.repository.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.embracon.teamposition.domain.entity.log.TeamSellerLog;

@Repository("teamSellerLogRepository")
public class TeamSellerLogRepositoryImpl extends
		AbstractHibernateRepostirory<TeamSellerLog> implements
		br.com.embracon.teamposition.domain.repository.TeamSellerLogRepository {

	
	private static final long serialVersionUID = 5605567186655680416L;

	public TeamSellerLogRepositoryImpl() {
		super(TeamSellerLog.class);
	}

	@Override
	public TeamSellerLog findByTeamSellerAndAtaId(Long teamSellerId, Long ataId) {
		
		Criteria criteria  = createCriteria();
		criteria.add(Restrictions.eq("teamSellerId", teamSellerId));
		criteria.add(Restrictions.eq("ata", ataId));
		
		return findBy(criteria);
	}

}
