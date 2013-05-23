package br.com.gp.inventory.domain.repository.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.gp.inventory.domain.entity.HardDisk;
import br.com.gp.inventory.domain.repository.HardDiskRepository;

@Repository("hardDiskRepository")
@Transactional(propagation = Propagation.REQUIRED)
public class HardDiskRepositoryImpl extends AbstractHibernateRepostirory<HardDisk> implements HardDiskRepository {

	private static final long serialVersionUID = 5742133623131280372L;

	public HardDiskRepositoryImpl() {
		super(HardDisk.class);
	}

}
