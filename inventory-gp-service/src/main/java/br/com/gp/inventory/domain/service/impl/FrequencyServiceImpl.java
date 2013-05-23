package br.com.gp.inventory.domain.service.impl;

import java.util.List;

import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Frequency;
import br.com.gp.inventory.domain.repository.FrequencyRepository;
import br.com.gp.inventory.domain.service.FrequencyService;


@Component("frequencyService")
@Interceptors(value = {ServiceInteceptor.class})
public class FrequencyServiceImpl implements FrequencyService {

	@Autowired
	@Qualifier(value = "frequencyRepository")
	private FrequencyRepository repository;
	
	@Override
	public List<Frequency> findAll() throws ServiceException {
		return (List<Frequency>) repository.findAll();
	}

	@Override
	public Frequency findById(Long id) throws ServiceException {
		return this.repository.findByIdentity(id);
	}

}
