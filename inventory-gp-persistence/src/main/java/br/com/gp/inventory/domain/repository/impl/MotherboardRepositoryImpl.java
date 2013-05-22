package br.com.gp.inventory.domain.repository.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.gp.inventory.domain.entity.Motherboard;
import br.com.gp.inventory.domain.repository.MotherboardRepository;

@Repository("motherboardRepository")
@Transactional(propagation = Propagation.REQUIRED)
public class MotherboardRepositoryImpl extends AbstractHibernateRepostirory<Motherboard> implements MotherboardRepository {

	private static final long serialVersionUID = 4534764295095424027L;

	protected MotherboardRepositoryImpl() {
		super(Motherboard.class);
	}

}
