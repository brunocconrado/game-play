package br.com.gp.inventory.domain.repository.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.gp.inventory.domain.entity.Tower;
import br.com.gp.inventory.domain.repository.TowerRepository;

@Repository("towerRepository")
@Transactional(propagation = Propagation.REQUIRED)
public class TowerRepositoryImpl extends AbstractHibernateRepostirory<Tower> implements TowerRepository {

	private static final long serialVersionUID = 8481787682527475267L;

	public TowerRepositoryImpl() {
		super(Tower.class);
	}

}
