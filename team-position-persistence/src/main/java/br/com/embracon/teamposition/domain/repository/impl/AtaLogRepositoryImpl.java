package br.com.embracon.teamposition.domain.repository.impl;

import org.springframework.stereotype.Repository;

import br.com.embracon.teamposition.domain.entity.log.AtaLog;
import br.com.embracon.teamposition.domain.repository.AtaLogRepository;

@Repository("ataLogRepository")
public class AtaLogRepositoryImpl extends AbstractHibernateRepostirory<AtaLog> implements AtaLogRepository {

	
	private static final long serialVersionUID = -9057016880333885372L;

	public AtaLogRepositoryImpl() {
		super(AtaLog.class);
	}

	
}
