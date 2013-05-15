package br.com.embracon.teamposition.domain.repository.jpa.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.embracon.teamposition.domain.entity.log.TeamSellerLog;
import br.com.embracon.teamposition.domain.repository.TeamSellerLogRepository;
import br.com.embracon.teamposition.domain.repository.jpa.AbstractPersistenceRepository;

@Stateless
@Local(TeamSellerLogRepository.class)
public class TeamSellerLogRepositoryImpl extends AbstractPersistenceRepository<TeamSellerLog> implements TeamSellerLogRepository {

	
	private static final long serialVersionUID = 5605567186655680416L;

	public TeamSellerLogRepositoryImpl() {
		super(TeamSellerLog.class);
	}

	@Override
	public TeamSellerLog findByTeamSellerAndAtaId(Long teamSellerId, Long ataId) {
		// TODO Auto-generated method stub
		return null;
	}

}
