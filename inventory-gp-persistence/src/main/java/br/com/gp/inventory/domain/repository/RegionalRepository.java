package br.com.gp.inventory.domain.repository;

import br.com.embracon.j4e.domain.repository.Repository;
import br.com.gp.inventory.domain.entity.tmp.Regional;

public interface RegionalRepository  extends Repository<Regional> {
	
	public Regional findByCode(String code);

}
