package br.com.gp.inventory.domain.service.impl;

import java.util.List;

import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Drive;
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
	public List<Processor> findAll() throws ServiceException {
		return (List<Processor>) repository.findAll();
	}

	@Override
	public void save(Processor processor) throws ServiceException {
		this.repository.save(processor);	
	}
	
	@Override
	public void delete(Processor processor) throws ServiceException {
		this.repository.delete(processor);
	}

}
