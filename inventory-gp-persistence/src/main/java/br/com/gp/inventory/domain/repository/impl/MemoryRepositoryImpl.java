package br.com.gp.inventory.domain.repository.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.gp.inventory.domain.entity.Memory;
import br.com.gp.inventory.domain.repository.MemoryRepository;

@Repository("memoryRepository")
@Transactional(propagation = Propagation.REQUIRED)
public class MemoryRepositoryImpl extends AbstractHibernateRepostirory<Memory> implements MemoryRepository {

	private static final long serialVersionUID = -2606399378940923523L;

	public MemoryRepositoryImpl() {
		super(Memory.class);
	}

}
