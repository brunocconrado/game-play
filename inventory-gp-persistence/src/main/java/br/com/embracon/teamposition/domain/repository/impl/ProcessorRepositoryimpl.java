package br.com.embracon.teamposition.domain.repository.impl;

import br.com.embracon.teamposition.domain.entity.Processor;
import br.com.embracon.teamposition.domain.repository.ProcessorRepository;

public class ProcessorRepositoryimpl  extends AbstractHibernateRepostirory<Processor> implements ProcessorRepository {

	private static final long serialVersionUID = 3693155610312651628L;

	public ProcessorRepositoryimpl() {
		super(Processor.class);
	}

}
