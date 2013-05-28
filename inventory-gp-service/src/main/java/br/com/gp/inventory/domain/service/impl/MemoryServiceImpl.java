package br.com.gp.inventory.domain.service.impl;

import java.util.List;

import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Memory;
import br.com.gp.inventory.domain.repository.MemoryRepository;
import br.com.gp.inventory.domain.service.MemoryService;

@Component("memoryService")
@Interceptors(value = {ServiceInteceptor.class})
public class MemoryServiceImpl implements MemoryService {
	
	
	@Autowired
	@Qualifier(value = "memoryRepository")
	private MemoryRepository reposiroty;

	@Override
	public void save(Memory memory) throws ServiceException {
		this.reposiroty.save(memory);
	}

	@Override
	public List<Memory> findAll() throws ServiceException {
		return (List<Memory>) this.reposiroty.findAll();
	}

	@Override
	public Memory findById(Long id) throws ServiceException {
		return this.reposiroty.findByIdentity(id);
	}

}
