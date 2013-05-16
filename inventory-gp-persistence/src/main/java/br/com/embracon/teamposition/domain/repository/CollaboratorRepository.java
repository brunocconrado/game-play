package br.com.embracon.teamposition.domain.repository;

import br.com.embracon.j4e.domain.repository.Repository;
import br.com.embracon.teamposition.domain.entity.Collaborator;

public interface CollaboratorRepository extends Repository<Collaborator> {

	public Collaborator findByRegistry(Integer registry);

	public Integer countTeam(Collaborator supervisor);

}
