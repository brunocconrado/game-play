package br.com.gp.inventory.domain.repository.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.gp.inventory.domain.entity.log.BranchLog;
import br.com.gp.inventory.domain.repository.BranchLogRepository;

@Repository("branchLogRepository")
@Transactional(propagation = Propagation.REQUIRED)
public class BranchLogRepositoryImpl extends AbstractHibernateRepostirory<BranchLog> implements BranchLogRepository {


	private static final long serialVersionUID = -1329088993905185759L;

	public BranchLogRepositoryImpl() {
		super(BranchLog.class);
	}

}
