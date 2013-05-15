package br.com.gp.inventory.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.embracon.j4e.domain.repository.RepositoryException;
import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.log.TeamSellerLog;
import br.com.gp.inventory.domain.repository.TeamSellerLogRepository;
import br.com.gp.inventory.domain.service.TeamSellerLogService;

@Component("teamSellerLogService")
public class TeamSellerLogServiceImpl implements TeamSellerLogService {

	@Autowired
	@Qualifier("teamSellerLogRepository")
	private TeamSellerLogRepository repository;
	
	@Override
	public TeamSellerLog findByTeamSellerIdAndAtaId(
				Long teamSellerId, Long ataId) throws ServiceException {
	
		try {
			return repository.findByTeamSellerAndAtaId(teamSellerId, ataId);
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}


}
