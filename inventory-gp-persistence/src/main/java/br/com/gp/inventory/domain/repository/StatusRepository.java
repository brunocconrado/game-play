package br.com.gp.inventory.domain.repository;

import br.com.embracon.j4e.domain.repository.Repository;
import br.com.gp.inventory.domain.entity.Status;
import br.com.gp.inventory.domain.enumeration.StatusEnum;

public interface StatusRepository extends Repository<Status> {

	public Status findBy(StatusEnum status);

}
