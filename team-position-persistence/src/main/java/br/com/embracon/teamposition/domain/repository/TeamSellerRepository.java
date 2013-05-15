package br.com.embracon.teamposition.domain.repository;

import java.util.List;

import br.com.embracon.j4e.domain.repository.Repository;
import br.com.embracon.teamposition.domain.entity.Ata;
import br.com.embracon.teamposition.domain.entity.TeamSeller;
import br.com.embracon.teamposition.domain.enumeration.StatusEnum;
import br.com.embracon.teamposition.domain.search.TeamSellerSearch;


public interface TeamSellerRepository  extends Repository<TeamSeller> {

	public List<TeamSeller> findAllTeamPositionByAta(Ata ata);

	public List<TeamSeller> findBy(TeamSellerSearch tsSearch, StatusEnum... status);
	
	public boolean hasTeamToAtaByBranchAndStatus(Long ataId, Long branchId, StatusEnum... status);

	public List<TeamSeller> findByBranchAtaAndStatus(
			Long branchId, Long ataId,	StatusEnum... status);

	public boolean findBy(Long branchIdSelected, Long id, Integer registry,
			StatusEnum inactive);

}
