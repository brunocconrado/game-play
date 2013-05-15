package br.com.gp.inventory.domain.service;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.log.TeamSellerLog;

public interface TeamSellerLogService {
	
	public TeamSellerLog findByTeamSellerIdAndAtaId(Long teamSellerId, Long ataId) throws ServiceException;

}
