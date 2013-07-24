package br.com.gp.inventory.domain.service.impl;

import java.util.List;

import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Drive;
import br.com.gp.inventory.domain.entity.HardDisk;
import br.com.gp.inventory.domain.repository.HardDiskRepository;
import br.com.gp.inventory.domain.service.HardDiskService;

@Component("hardDiskService")
@Interceptors(value = {ServiceInteceptor.class})
public class HardDiskServiceImpl implements HardDiskService {

	@Autowired
	@Qualifier(value = "hardDiskRepository")
	private HardDiskRepository repository;

	@Override
	public void save(HardDisk hardDisk) throws ServiceException {
		this.repository.save(hardDisk);
	}

	@Override
	public List<HardDisk> findAll() throws ServiceException {
		return (List<HardDisk>) this.repository.findAll();
	}
	
	@Override
	public void delete(HardDisk hardDisk) throws ServiceException {
		this.repository.delete(hardDisk);
	}
	
}
