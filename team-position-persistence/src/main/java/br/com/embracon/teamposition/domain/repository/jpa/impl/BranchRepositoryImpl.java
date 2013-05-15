package br.com.embracon.teamposition.domain.repository.jpa.impl;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import br.com.embracon.j4e.domain.repository.RepositoryException;
import br.com.embracon.teamposition.domain.entity.Branch;
import br.com.embracon.teamposition.domain.entity.Status;
import br.com.embracon.teamposition.domain.enumeration.StatusEnum;
import br.com.embracon.teamposition.domain.repository.BranchRepository;
import br.com.embracon.teamposition.domain.repository.jpa.AbstractPersistenceRepository;
import br.com.embracon.teamposition.domain.search.BranchSearch;

@Stateless
@Local(BranchRepository.class)
@SuppressWarnings({"unchecked","rawtypes"})	
public class BranchRepositoryImpl extends AbstractPersistenceRepository<Branch> implements BranchRepository {

	
	
	private static final long serialVersionUID = 4294318837808309787L;

	public BranchRepositoryImpl() {
		super(Branch.class);
	}

	@Override
	public List<Branch> findAllActive() {
		try {
			
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Branch> c = cb.createQuery(Branch.class);

			Root<Branch> branch = c.from(Branch.class);
			Join<Branch, Status> status =  branch.join("status");

			c.where(cb.equal(status.get("id"), cb.parameter(Long.class, "id")));

			TypedQuery q = entityManager.createQuery(c);
			q.setParameter("id", StatusEnum.ACTIVE.id());

			return q.getResultList();
		} catch (Exception e) {
			throw new RepositoryException(e);
		}
	}

	@Override
	public List<Branch> searchBy(BranchSearch search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Branch findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existFilial(Branch branch) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Branch searchByCollaboratorRegistry(Integer registry) {
		// TODO Auto-generated method stub
		return null;
	}

}