package br.com.gp.inventory.domain.service.impl;

import java.util.List;

import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Potential;
import br.com.gp.inventory.domain.repository.PotentialRepository;
import br.com.gp.inventory.domain.service.PotentialService;

@Component("potentialService")
@Interceptors(value = {ServiceInteceptor.class})
public class PotentialServiceImpl implements PotentialService {

	@Autowired
	@Qualifier("potentialRepository")
	private PotentialRepository repository;
	
	@Override
	public List<Potential> findAll() throws ServiceException {
		return (List<Potential>) repository.findAll();
	}

	@Override
	public Potential findById(Long potentialId) throws ServiceException {
		return this.repository.findByIdentity(potentialId);
	}

	@Override
	public Potential findOrCreateByName(String name) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
