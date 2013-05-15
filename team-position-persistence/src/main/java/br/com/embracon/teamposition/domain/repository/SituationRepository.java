package br.com.embracon.teamposition.domain.repository;

import java.util.List;

import br.com.embracon.j4e.domain.repository.Repository;
import br.com.embracon.teamposition.domain.entity.Situation;


public interface SituationRepository extends Repository<Situation> {

	public List<Situation> findByType(Character seller);

}
