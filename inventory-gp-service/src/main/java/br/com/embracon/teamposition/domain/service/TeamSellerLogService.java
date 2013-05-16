package br.com.embracon.teamposition.domain.service;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.embracon.teamposition.domain.entity.log.TeamSellerLog;

public interface TeamSellerLogService {
	
	public TeamSellerLog findByTeamSellerIdAndAtaId(Long teamSellerId, Long ataId) throws ServiceException;

}
