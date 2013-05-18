package br.com.gp.inventory.domain.repository.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.gp.inventory.domain.entity.Status;
import br.com.gp.inventory.domain.enumeration.StatusEnum;
import br.com.gp.inventory.domain.repository.StatusRepository;

@Repository("statusRepository")
@Transactional(propagation = Propagation.REQUIRED)
public class StatusRepositoryImpl extends AbstractHibernateRepostirory<Status> implements StatusRepository {

	private static final long serialVersionUID = 2656949088779818932L;

	private static Map<Long, Status> inMemory = new HashMap<Long, Status>();
	
	public StatusRepositoryImpl() {
		super(Status.class);
	}
	
	@Override
	public Status findBy(StatusEnum statusEnum) {
		
		if(inMemory.isEmpty()) {
			loadInMemory();
		}
		
		Status status = inMemory.get(statusEnum.id());
		if(status == null) {
			status = findByIdentity(statusEnum.id());
		}
		return status;
	}
	
	private void loadInMemory() {
		inMemory.clear();
		for(Status status : findAll()) {
			inMemory.put(status.getId(), status);
		}
	}
}
