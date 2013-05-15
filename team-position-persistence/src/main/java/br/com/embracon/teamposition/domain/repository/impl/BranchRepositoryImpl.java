package br.com.embracon.teamposition.domain.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.embracon.j4e.domain.repository.RepositoryException;
import br.com.embracon.teamposition.domain.entity.Branch;
import br.com.embracon.teamposition.domain.enumeration.StatusEnum;
import br.com.embracon.teamposition.domain.repository.BranchRepository;
import br.com.embracon.teamposition.domain.repository.StatusRepository;
import br.com.embracon.teamposition.domain.search.BranchSearch;

@Repository("branchRepository")
@SuppressWarnings("unchecked")
@Transactional(propagation = Propagation.REQUIRED)
public class BranchRepositoryImpl extends AbstractHibernateRepostirory<Branch> implements BranchRepository {

	private static final long serialVersionUID = 5149741600765034719L;
	
	@Autowired
	@Qualifier("statusRepository")
	private StatusRepository statusRepository;

	public BranchRepositoryImpl() {
		super(Branch.class);
	}

	public List<Branch> findAllActive() {
		return listBy(Restrictions.eq("status", 
				statusRepository.findBy(StatusEnum.ACTIVE)));
	}

	public List<Branch> searchBy(BranchSearch search) {
		
		try {
			
			Criteria criteria = createCriteria();
			if(search.getCode() != null && !search.getCode().isEmpty()) {
				criteria.add(Restrictions.ilike("code", search.getCode(), MatchMode.ANYWHERE));
			}
			
			if(search.getaRegionalCode() != null && !search.getaRegionalCode().isEmpty()) {
				criteria.add(Restrictions.ilike("aRegional.code", search.getaRegionalCode(), MatchMode.ANYWHERE));
			}
			
			if(search.getManagerCode() != null && !search.getManagerCode().isEmpty()) {
				criteria.add(Restrictions.ilike("regional.registry", search.getManagerCode(), MatchMode.ANYWHERE));
			}
			
			if(search.getRegionalRegister() != null && !search.getRegionalRegister().isEmpty()) {
				criteria.add(Restrictions.ilike("manager.registry", search.getRegionalRegister(), MatchMode.ANYWHERE));
			}
			
			return criteria.list();
		
		} catch (Exception e) {
			throw new RepositoryException(e);
		}
	}

	@Override
	public Branch findByCode(String code) {
		return null;
	}

	@Override
	public boolean existFilial(Branch branch) {
		
		try {
			
			Criteria criteria = createCriteria().add(Restrictions.and(
					Restrictions.eq("code", branch.getCode()),
					Restrictions.ne("id", branch.getId())))
					.setProjection(Projections.count("id"));

			return (Long)criteria.uniqueResult() > 0;
		} catch (Exception e) {
			throw new RepositoryException(e);
		}
	}

	@Override
	public Branch searchByCollaboratorRegistry(Integer registry) {
		
		Criteria criteria = createCriteria("branch");
		criteria = criteria.createCriteria("branch.manager", "manager", JoinType.INNER_JOIN);
		criteria.add(Restrictions.eq("manager.registry", registry));
		
		return findBy(criteria);
	}

}
