package br.com.gp.inventory.domain.repository;

import br.com.embracon.j4e.domain.repository.Repository;
import br.com.gp.inventory.domain.entity.tmp.Collaborator;

public interface CollaboratorRepository extends Repository<Collaborator> {

	public Collaborator findByRegistry(Integer registry);

	public Integer countTeam(Collaborator supervisor);

}
