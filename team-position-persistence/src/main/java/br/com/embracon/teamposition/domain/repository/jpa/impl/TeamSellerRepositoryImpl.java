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
import br.com.embracon.teamposition.domain.entity.Ata;
import br.com.embracon.teamposition.domain.entity.TeamSeller;
import br.com.embracon.teamposition.domain.enumeration.StatusEnum;
import br.com.embracon.teamposition.domain.repository.TeamSellerRepository;
import br.com.embracon.teamposition.domain.repository.jpa.AbstractPersistenceRepository;
import br.com.embracon.teamposition.domain.search.TeamSellerSearch;

@Stateless
@Local(TeamSellerRepository.class)
@SuppressWarnings({"rawtypes", "unchecked"})
public class TeamSellerRepositoryImpl  extends AbstractPersistenceRepository<TeamSeller> implements TeamSellerRepository {

	private static final long serialVersionUID = -7309686050228286244L;

	public TeamSellerRepositoryImpl() {
		super(TeamSeller.class);
	}

	
	@Override
	public List<TeamSeller> findAllTeamPositionByAta(Ata ata) {
		
		try {
			
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	        CriteriaQuery<TeamSeller> c = cb.createQuery(TeamSeller.class);
	        Root<TeamSeller> venda = c.from(TeamSeller.class);

	        Join<TeamSeller, Ata> ataJoin = venda.join("ata");

	        c.where(cb.equal(ataJoin.get("id"), cb.parameter(Long.class, "id")));

	        TypedQuery q = entityManager.createQuery(c);
	        q.setParameter("id", ata.getId());

	        return q.getResultList();
		} catch (Exception e) {
			throw new RepositoryException(e);
		}
	}

	@Override
	public List<TeamSeller> findByBranchAtaAndStatus(Long branchId, Long ataId,
			StatusEnum... status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TeamSeller> findBy(TeamSellerSearch tsSearch,
			StatusEnum... status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean findBy(Long branchIdSelected, Long id, Integer registry,
			StatusEnum inactive) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasTeamToAtaByBranchAndStatus(Long ataId, Long branchId,
			StatusEnum... status) {
		// TODO Auto-generated method stub
		return false;
	}

}
