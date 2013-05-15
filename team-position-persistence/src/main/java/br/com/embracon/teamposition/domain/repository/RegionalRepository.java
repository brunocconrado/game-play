package br.com.embracon.teamposition.domain.repository;

import br.com.embracon.j4e.domain.repository.Repository;
import br.com.embracon.teamposition.domain.entity.Regional;

public interface RegionalRepository  extends Repository<Regional> {
	
	public Regional findByCode(String code);

}
