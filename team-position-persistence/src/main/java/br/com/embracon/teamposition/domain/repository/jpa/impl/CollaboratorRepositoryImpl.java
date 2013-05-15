package br.com.embracon.teamposition.domain.repository.jpa.impl;

import javax.ejb.Stateless;

import br.com.embracon.teamposition.domain.entity.Collaborator;
import br.com.embracon.teamposition.domain.repository.CollaboratorRepository;
import br.com.embracon.teamposition.domain.repository.jpa.AbstractPersistenceRepository;

@Stateless
public class CollaboratorRepositoryImpl extends AbstractPersistenceRepository<Collaborator> implements CollaboratorRepository {

	private static final long serialVersionUID = -1094583941344277546L;

	public CollaboratorRepositoryImpl() {
		super(Collaborator.class);
	}

	@Override
	public Collaborator findByRegistry(Integer registry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer countTeam(Collaborator supervisor) {
		// TODO Auto-generated method stub
		return null;
	}

}
