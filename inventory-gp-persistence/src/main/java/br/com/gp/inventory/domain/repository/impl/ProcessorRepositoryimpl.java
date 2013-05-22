package br.com.gp.inventory.domain.repository.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.gp.inventory.domain.entity.Processor;
import br.com.gp.inventory.domain.repository.ProcessorRepository;

@Repository("processorRepository")
@Transactional(propagation = Propagation.REQUIRED)
public class ProcessorRepositoryImpl  extends AbstractHibernateRepostirory<Processor> implements ProcessorRepository {

	private static final long serialVersionUID = 3693155610312651628L;

	public ProcessorRepositoryImpl() {
		super(Processor.class);
	}

}
