package br.com.embracon.teamposition.domain.repository.jpa.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.embracon.teamposition.domain.entity.Status;
import br.com.embracon.teamposition.domain.enumeration.StatusEnum;
import br.com.embracon.teamposition.domain.repository.StatusRepository;
import br.com.embracon.teamposition.domain.repository.jpa.AbstractPersistenceRepository;

@Stateless
@Local(StatusRepository.class)
public class StatusRepositoryImpl extends AbstractPersistenceRepository<Status> implements StatusRepository {

	private static final long serialVersionUID = -4391969807065296410L;

	public StatusRepositoryImpl() {
		super(Status.class);
	}

	@Override
	public Status findBy(StatusEnum status) {
		// TODO Auto-generated method stub
		return null;
	}

}
