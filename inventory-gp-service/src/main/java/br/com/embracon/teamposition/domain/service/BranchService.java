package br.com.embracon.teamposition.domain.service;

import java.util.List;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.embracon.teamposition.domain.entity.Branch;
import br.com.embracon.teamposition.domain.event.EventListener;
import br.com.embracon.teamposition.domain.event.Listener;
import br.com.embracon.teamposition.domain.search.BranchSearch;
import br.com.embracon.teamposition.domain.vo.UserSession;

public interface BranchService {
	
	public Branch findBranch(String code) throws ServiceException;

	public List<Branch> findAllActive() throws ServiceException;

	public void save(Branch branch, UserSession editor) throws ServiceException;

	public List<Branch> searchBy(BranchSearch search) throws ServiceException;

	public Branch findById(Long branchIdSelected) throws ServiceException;

	public void remove(Branch branch) throws ServiceException;
	
	public void update(Branch branch) throws ServiceException;

	@SuppressWarnings("rawtypes")
	public Listener<EventListener> getTrigger();

	public Branch findByCollaboratorRegistry(Integer registry) throws ServiceException;

}
