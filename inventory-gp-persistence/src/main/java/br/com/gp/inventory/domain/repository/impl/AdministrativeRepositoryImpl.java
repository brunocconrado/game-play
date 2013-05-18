package br.com.gp.inventory.domain.repository.impl;

import org.springframework.stereotype.Repository;

import br.com.gp.inventory.domain.entity.tmp.Administrative;
import br.com.gp.inventory.domain.repository.AdministrativeRepository;

@Repository("administrativeRepository")
public class AdministrativeRepositoryImpl  extends AbstractHibernateRepostirory<Administrative> implements AdministrativeRepository {


	private static final long serialVersionUID = 4071854392731316962L;

	public AdministrativeRepositoryImpl() {
		super(Administrative.class);
	}

}
