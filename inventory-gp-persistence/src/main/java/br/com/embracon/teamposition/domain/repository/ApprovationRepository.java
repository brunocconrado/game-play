package br.com.embracon.teamposition.domain.repository;

import br.com.embracon.j4e.domain.repository.Repository;
import br.com.embracon.teamposition.domain.entity.tmp.Approvation;

public interface ApprovationRepository extends Repository<Approvation> {

	public Approvation findByBranchAndAtaId(Long branchId, Long ataId);

}
