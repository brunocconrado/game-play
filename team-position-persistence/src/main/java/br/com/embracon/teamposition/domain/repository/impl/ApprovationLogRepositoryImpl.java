package br.com.embracon.teamposition.domain.repository.impl;

import org.springframework.stereotype.Repository;

import br.com.embracon.teamposition.domain.entity.log.ApprovationLog;
import br.com.embracon.teamposition.domain.repository.ApprovationLogRepository;

@Repository("approvationLogRepository")
public class ApprovationLogRepositoryImpl extends AbstractHibernateRepostirory<ApprovationLog> 
		implements ApprovationLogRepository {

	private static final long serialVersionUID = 8507735319476951303L;

	public ApprovationLogRepositoryImpl() {
		super(ApprovationLog.class);
	}

}