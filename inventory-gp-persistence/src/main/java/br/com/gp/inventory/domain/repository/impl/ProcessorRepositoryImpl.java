package br.com.gp.inventory.domain.repository.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.gp.inventory.domain.entity.Processor;
import br.com.gp.inventory.domain.entity.Socket;
import br.com.gp.inventory.domain.repository.ProcessorRepository;

@SuppressWarnings("unchecked")
@Repository("processorRepository")
@Transactional(propagation = Propagation.REQUIRED)
public class ProcessorRepositoryImpl  extends AbstractHibernateRepostirory<Processor> implements ProcessorRepository {

	private static final long serialVersionUID = 3693155610312651628L;

	public ProcessorRepositoryImpl() {
		super(Processor.class);
	}

	@Override
	public List<Processor> findBySocket(Socket socket) {
		return createCriteria().add(Restrictions.eq("socket.id", socket.getId())).list();
	}

}
