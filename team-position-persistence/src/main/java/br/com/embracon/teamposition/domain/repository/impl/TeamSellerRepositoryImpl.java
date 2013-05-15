package br.com.embracon.teamposition.domain.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.embracon.teamposition.domain.entity.Ata;
import br.com.embracon.teamposition.domain.entity.Branch;
import br.com.embracon.teamposition.domain.entity.TeamSeller;
import br.com.embracon.teamposition.domain.enumeration.StatusEnum;
import br.com.embracon.teamposition.domain.repository.TeamSellerRepository;
import br.com.embracon.teamposition.domain.search.TeamSellerSearch;

@Repository("teamSellerRepository")
public class TeamSellerRepositoryImpl extends AbstractHibernateRepostirory<TeamSeller> implements TeamSellerRepository {

	private static final long serialVersionUID = -3695664013397718318L;
	
	private String alias = TeamSeller.class.getSimpleName();

	public TeamSellerRepositoryImpl() {
		super(TeamSeller.class);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<TeamSeller> findAllTeamPositionByAta(Ata ata) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<TeamSeller> findBy(TeamSellerSearch search, StatusEnum... status) {

		Criteria criteria = createCriteria("teamSeller");
		criteria = criteria.createCriteria("seller", "seller", JoinType.LEFT_OUTER_JOIN);
		if(search.getSellerName() != null && !search.getSellerName().isEmpty()) {
			criteria.add(Restrictions.like("seller.name", 
					search.getSellerName().toUpperCase().trim(), MatchMode.ANYWHERE));
		}

		if(search.getSellerRegsitry() != null && !search.getSellerRegsitry().isEmpty()) {
			criteria.add(Restrictions.eq("seller.registry", 
					Integer.parseInt(search.getSellerRegsitry().trim())));
		}

		criteria = criteria.createCriteria("teamSeller.supervisor", "supervisor", JoinType.LEFT_OUTER_JOIN);
		if(search.getSellerName() != null && !search.getSellerName().isEmpty()) {
			criteria.add(Restrictions.like("supervisor.name", 
					search.getSupervisorName().toUpperCase().trim(), MatchMode.ANYWHERE));
		}

		if(search.getSellerRegsitry() != null && !search.getSellerRegsitry().isEmpty()) {
			criteria.add(Restrictions.eq("supervisor.registry", 
					Integer.parseInt(search.getSupervisorRegistry().trim())));
		}

		criteria = criteria.createCriteria("teamSeller.status", "status", JoinType.INNER_JOIN);
		criteria.add(Restrictions.in("status.id", toListId(status)));
		
		if(search.getBranchId() != null) {
			criteria = criteria.createCriteria("teamSeller.branch", 
					Branch.class.getSimpleName(), JoinType.INNER_JOIN);
			criteria.add(Restrictions.eq(
					Branch.class.getSimpleName() + ".id", search.getBranchId()));
		}
		
		return listBy(criteria);
	}

	@Override 
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean hasTeamToAtaByBranchAndStatus(Long ataId, Long branchId, StatusEnum... status) {

		Criteria criteria = createCriteria("teamPosition");
		criteria = criteria.createCriteria("ata", "ata", JoinType.INNER_JOIN);
		criteria.add(Restrictions.eq("ata.id", ataId));

		criteria = criteria.createCriteria("teamPosition.status", "status", JoinType.INNER_JOIN);
		criteria.add(Restrictions.in("status.id", toListId(status)));
		
		criteria = criteria.createCriteria("teamPosition.branch", "branch", JoinType.INNER_JOIN);
		criteria.add(Restrictions.eq("branch.id", branchId));

		criteria.setProjection(Projections.count("teamPosition.id"));

		return (Long)criteria.uniqueResult() > 0;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<TeamSeller> findByBranchAtaAndStatus(
			Long branchId, Long ataId, StatusEnum... status) {
		
		Criteria criteria = createCriteria("teamPosition");
		criteria = criteria.createCriteria("teamPosition.ata", "ata", JoinType.INNER_JOIN);
		criteria.add(Restrictions.eq("ata.id", ataId));
		
		criteria = criteria.createCriteria("teamPosition.branch", "branch", JoinType.INNER_JOIN); 
		criteria.add(Restrictions.eq("branch.id", branchId));
		
		criteria = criteria.createCriteria("teamPosition.status", "status", JoinType.INNER_JOIN); 
		criteria.add(Restrictions.in("status.id", toListId(status)));
		
		return listBy(criteria);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean findBy(Long branchIdSelected, Long ataId, Integer collaboratorRegistry,	StatusEnum inactive) {

		Criteria criteria = createCriteria(alias);
		criteria = criteria.createCriteria(alias + ".branch", "branch", JoinType.INNER_JOIN);
		criteria.add(Restrictions.ne("branch.id", branchIdSelected));

		criteria = criteria.createCriteria(alias + ".seller", "seller", JoinType.INNER_JOIN);
		criteria.add(Restrictions.eq("seller.registry", collaboratorRegistry));
		
		criteria = criteria.createCriteria(alias + ".status", "status", JoinType.INNER_JOIN);
		criteria.add(Restrictions.ne("status.id", inactive.id()));
		
		criteria.setProjection(Projections.count(alias + ".id"));

		return (Long)criteria.uniqueResult() > 0;
	}
	
	private List<Long> toListId(StatusEnum... status) {
		List<Long> ids = new ArrayList<Long>();
		for(StatusEnum sEnum : status) {
			ids.add(sEnum.id());
		}
		
		return ids;
	}
}
