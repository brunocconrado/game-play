package br.com.gp.inventory.domain.repository.impl;

import org.springframework.stereotype.Repository;

import br.com.gp.inventory.domain.entity.log.ApprovationLog;
import br.com.gp.inventory.domain.repository.ApprovationLogRepository;

@Repository("approvationLogRepository")
public class ApprovationLogRepositoryImpl extends AbstractHibernateRepostirory<ApprovationLog> 
		implements ApprovationLogRepository {

	private static final long serialVersionUID = 8507735319476951303L;

	public ApprovationLogRepositoryImpl() {
		super(ApprovationLog.class);
	}

}