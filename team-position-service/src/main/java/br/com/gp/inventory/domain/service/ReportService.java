package br.com.gp.inventory.domain.service;

import java.util.List;

import br.com.embracon.j4e.io.ByteBuffer;
import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Ata;
import br.com.gp.inventory.domain.entity.TeamSeller;
import br.com.gp.inventory.domain.enumeration.StatusEnum;
import br.com.gp.inventory.domain.search.TeamSellerSearch;
import br.com.gp.inventory.domain.vo.UserSession;

public interface ReportService {

	public List<TeamSeller> search(TeamSellerSearch search) throws ServiceException;

	public ByteBuffer export(Ata ata, TeamSellerSearch search, boolean compareChanges) throws ServiceException;

	List<TeamSeller> search(TeamSellerSearch search, StatusEnum... status) throws ServiceException;

	void approveReprove(TeamSellerSearch search, String message, boolean approved, UserSession user) throws ServiceException;

}
