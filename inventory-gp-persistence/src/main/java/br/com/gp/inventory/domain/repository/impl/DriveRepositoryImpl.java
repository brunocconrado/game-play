package br.com.gp.inventory.domain.repository.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.gp.inventory.domain.entity.Drive;
import br.com.gp.inventory.domain.repository.DriveRepository;

@Repository("driveRepository")
@Transactional(propagation = Propagation.REQUIRED)
public class DriveRepositoryImpl extends AbstractHibernateRepostirory<Drive> implements DriveRepository {

	private static final long serialVersionUID = 3619264343113465044L;

	public DriveRepositoryImpl() {
		super(Drive.class);
	}

}
