package br.com.embracon.teamposition.domain.repository.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.embracon.teamposition.domain.entity.Processor;
import br.com.embracon.teamposition.domain.repository.ProcessorRepository;

@Repository("processorRepository")
@Transactional(propagation = Propagation.REQUIRED)
public class ProcessorRepositoryimpl  extends AbstractHibernateRepostirory<Processor> implements ProcessorRepository {

	private static final long serialVersionUID = 3693155610312651628L;

	public ProcessorRepositoryimpl() {
		super(Processor.class);
	}

}
