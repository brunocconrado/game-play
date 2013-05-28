package br.com.gp.inventory.domain.service.impl;

import java.util.List;

import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Drive;
import br.com.gp.inventory.domain.repository.DriveRepository;
import br.com.gp.inventory.domain.service.DriveService;

@Component("driveService")
@Interceptors(value = {ServiceInteceptor.class})
public class DriveServiceImpl implements DriveService {

	@Autowired
	@Qualifier(value = "driveRepository")
	private DriveRepository repository;
	
	@Override
	public void save(Drive drive) throws ServiceException {
		this.repository.save(drive);
	}

	@Override
	public List<Drive> findAll() throws ServiceException {
		return (List<Drive>) this.repository.findAll();
	}

	@Override
	public Drive findById(Long id) throws ServiceException {
		return this.repository.findByIdentity(id);
	}

}
