package br.com.embracon.teamposition.domain.repository;

import br.com.embracon.j4e.domain.repository.Repository;
import br.com.embracon.teamposition.domain.entity.Status;
import br.com.embracon.teamposition.domain.enumeration.StatusEnum;

public interface StatusRepository extends Repository<Status> {

	public Status findBy(StatusEnum status);

}
