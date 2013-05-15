package br.com.embracon.teamposition.domain.service;

import java.util.List;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.embracon.teamposition.domain.entity.Collaborator;
import br.com.embracon.teamposition.domain.entity.TeamSeller;
import br.com.embracon.teamposition.domain.enumeration.StatusEnum;
import br.com.embracon.teamposition.domain.search.TeamSellerSearch;

public interface TeamSellerService {

	public List<TeamSeller> search(TeamSellerSearch tsSearch, StatusEnum... status) throws ServiceException;

	public void remove(TeamSeller teamSellerToDelete) throws ServiceException;

	public void processTeamSeller() throws ServiceException;

	public boolean validateRegistryPeriod() throws ServiceException;

	public void existCollaboratorInOtherBranch(Collaborator seller, Long branchIdSelected) throws ServiceException;

	public void save(TeamSeller teamSeller) throws ServiceException;

	public void concludeRegistry(Long branchId, StatusEnum status) throws ServiceException;

	public void concludeRegistry(Long branchId, StatusEnum statusEnum, Collaborator approver, String message, boolean isAprovation) throws ServiceException;

	public boolean isCloseByUser(Long branchId, Long ataId, StatusEnum... status) throws ServiceException;
}
