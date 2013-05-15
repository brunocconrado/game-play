package br.com.embracon.teamposition.domain.repository.jpa.impl;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.embracon.teamposition.domain.entity.Situation;
import br.com.embracon.teamposition.domain.repository.SituationRepository;
import br.com.embracon.teamposition.domain.repository.jpa.AbstractPersistenceRepository;

@Stateless
@Local(SituationRepository.class)
public class SituationRepositoryImpl extends AbstractPersistenceRepository<Situation> implements SituationRepository {

	private static final long serialVersionUID = 3343092284368317397L;

	public SituationRepositoryImpl() {
		super(Situation.class);
	}

	@Override
	public List<Situation> findByType(Character seller) {
		// TODO Auto-generated method stub
		return null;
	}

}
