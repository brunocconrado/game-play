package br.com.gp.inventory.domain.service.impl;

import java.util.Collection;

import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Processor;
import br.com.gp.inventory.domain.repository.ProcessorRepository;
import br.com.gp.inventory.domain.service.ProcessorService;

@Component("processorService")
@Interceptors(value = {ServiceInteceptor.class})
public class ProcessorServiceImpl implements ProcessorService {

	@Autowired
	@Qualifier(value = "processorRepository")
	private ProcessorRepository repository;
	
	@Override
	public Collection<Processor> findAll() throws ServiceException {
		return repository.findAll();
	}

}
