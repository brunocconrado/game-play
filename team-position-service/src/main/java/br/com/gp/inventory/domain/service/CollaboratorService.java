package br.com.gp.inventory.domain.service;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Collaborator;
import br.com.gp.inventory.domain.enumeration.SoldCodeEnum;
import br.com.gp.inventory.domain.search.BranchSearch;
import br.com.gp.inventory.domain.search.CollaboratorSearch;

public interface CollaboratorService {

	public Collaborator findCollaborator(CollaboratorSearch search) throws ServiceException;
	
	public Collaborator findCollaborator(Integer registry, SoldCodeEnum type) throws ServiceException;

	public Collaborator findCollaborator(CollaboratorSearch search, SoldCodeEnum soldCode, boolean isToValidateEmail) throws ServiceException;

	public void save(Collaborator regional) throws ServiceException;

	public Integer countTeam(Collaborator supervisor) throws ServiceException;

	public Collaborator findByRegistry(Integer registry) throws ServiceException;

	public Collaborator findCollaborator(BranchSearch branchSearch,	SoldCodeEnum manager)  throws ServiceException;

}
