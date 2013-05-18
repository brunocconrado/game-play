package br.com.gp.inventory.domain.repository;

import java.util.List;

import br.com.embracon.j4e.domain.repository.Repository;
import br.com.gp.inventory.domain.entity.tmp.SoldCode;
import br.com.gp.inventory.domain.enumeration.SoldCodeEnum;

public interface SoldCodeRepository extends Repository<SoldCode>{

	public List<SoldCode> listByType(SoldCodeEnum type);

	public SoldCode findByCode(String code);
}
