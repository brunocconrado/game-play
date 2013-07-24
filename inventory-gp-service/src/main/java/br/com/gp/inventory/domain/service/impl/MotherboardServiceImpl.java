package br.com.gp.inventory.domain.service.impl;

import java.util.List;

import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Drive;
import br.com.gp.inventory.domain.entity.Motherboard;
import br.com.gp.inventory.domain.repository.MotherboardRepository;
import br.com.gp.inventory.domain.service.MotherboardService;

@Component("motherboardService")
@Interceptors(value = {ServiceInteceptor.class})
public class MotherboardServiceImpl implements MotherboardService {

	@Autowired
	@Qualifier(value = "motherboardRepository")
	private MotherboardRepository repository;
	
	@Override
	public List<Motherboard> findAll() throws ServiceException {
		return (List<Motherboard>) repository.findAll();
	}

	@Override
	public void save(Motherboard motherboard) throws ServiceException {
		this.repository.save(motherboard);	
	}
	
	@Override
	public void delete(Motherboard motherboard) throws ServiceException {
		this.repository.delete(motherboard);
	}
}
