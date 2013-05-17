package br.com.embracon.teamposition.domain.repository;

import java.util.List;

import br.com.embracon.j4e.domain.repository.Repository;
import br.com.embracon.teamposition.domain.entity.tmp.SoldCode;
import br.com.embracon.teamposition.domain.enumeration.SoldCodeEnum;

public interface SoldCodeRepository extends Repository<SoldCode>{

	public List<SoldCode> listByType(SoldCodeEnum type);

	public SoldCode findByCode(String code);
}
