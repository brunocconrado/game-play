package br.com.embracon.teamposition.domain.service;

import java.util.Date;
import java.util.List;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.embracon.teamposition.domain.entity.Ata;
import br.com.embracon.teamposition.domain.event.EventListener;
import br.com.embracon.teamposition.domain.event.Listener;
import br.com.embracon.teamposition.domain.search.AtaSearch;


public interface AtaService {

	public List<Ata> searchBy(AtaSearch search) throws ServiceException;
	
	public void save(Ata ata) throws ServiceException;

	public void remove(Ata ataSelected) throws ServiceException;
	
	public Ata findByRegistryDate(Date today) throws ServiceException;

	public List<Ata> findAll() throws ServiceException;
	
	public Ata findByMonthAndYear(Integer month, Integer year) throws ServiceException;
	
	public Ata findById(Long ataId, boolean loadLazyDependences) throws ServiceException;
	
	@SuppressWarnings("rawtypes")
	public Listener<EventListener> getTrigger();

}