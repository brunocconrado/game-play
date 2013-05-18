package br.com.gp.inventory.domain.repository;

import java.util.List;

import br.com.embracon.j4e.domain.repository.Repository;
import br.com.gp.inventory.domain.entity.tmp.Situation;


public interface SituationRepository extends Repository<Situation> {

	public List<Situation> findByType(Character seller);

}
