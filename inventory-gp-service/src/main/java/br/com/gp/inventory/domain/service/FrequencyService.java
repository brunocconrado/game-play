package br.com.gp.inventory.domain.service;

import java.util.List;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Frequency;

public interface FrequencyService {

	public List<Frequency> findAll() throws ServiceException;

	public Frequency findById(Long id) throws ServiceException;

	public Frequency findOrCreateByName(String name) throws ServiceException;

	public Frequency save(Frequency frequency) throws ServiceException;

	public Frequency findByName(String name);

}
