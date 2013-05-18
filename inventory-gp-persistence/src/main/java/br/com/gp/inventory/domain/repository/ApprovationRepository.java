package br.com.gp.inventory.domain.repository;

import br.com.embracon.j4e.domain.repository.Repository;
import br.com.gp.inventory.domain.entity.tmp.Approvation;

public interface ApprovationRepository extends Repository<Approvation> {

	public Approvation findByBranchAndAtaId(Long branchId, Long ataId);

}
