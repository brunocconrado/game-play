package br.com.embracon.teamposition.domain.repository;

import br.com.embracon.j4e.domain.repository.Repository;
import br.com.embracon.teamposition.domain.entity.log.TeamSellerLog;

public interface TeamSellerLogRepository extends Repository<TeamSellerLog> {

	public TeamSellerLog findByTeamSellerAndAtaId(Long teamSellerId, Long ataId);

}
