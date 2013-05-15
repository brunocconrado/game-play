package br.com.embracon.teamposition.domain.repository.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.embracon.teamposition.domain.entity.SoldCode;
import br.com.embracon.teamposition.domain.enumeration.SoldCodeEnum;
import br.com.embracon.teamposition.domain.repository.SoldCodeRepository;

@Repository("soldCodeRepository")
@Transactional(propagation = Propagation.REQUIRED)
public class SoldCodeRepositoryImpl extends 
			AbstractHibernateRepostirory<SoldCode>implements SoldCodeRepository {

	private static final long serialVersionUID = 940717221100179431L;

	public SoldCodeRepositoryImpl() {
		super(SoldCode.class);
	}

	public List<SoldCode> listByType(SoldCodeEnum soldCodeEnum) {
		return listBy(Restrictions.eq("type", soldCodeEnum.type()));
	}

	@Override
	public SoldCode findByCode(String code) {
		return findBy(Restrictions.eq("code", code));
	}

}
