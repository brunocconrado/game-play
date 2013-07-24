package br.com.gp.inventory.domain.service;

import java.util.List;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Potential;

public interface PotentialService {

	public List<Potential> findAll() throws ServiceException;

	public Potential findById(Long potentialId) throws ServiceException;

	public Potential findOrCreateByName(String name) throws ServiceException;

	public Potential findByName(String name) throws ServiceException;
	
}
