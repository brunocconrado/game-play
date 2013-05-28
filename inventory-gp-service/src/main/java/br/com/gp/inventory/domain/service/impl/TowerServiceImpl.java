package br.com.gp.inventory.domain.service.impl;

import java.util.List;

import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Tower;
import br.com.gp.inventory.domain.repository.TowerRepository;
import br.com.gp.inventory.domain.service.TowerService;

@Component("towerService")
@Interceptors(value = {ServiceInteceptor.class})
public class TowerServiceImpl implements TowerService {

	@Autowired
	@Qualifier(value = "towerRepository")
	private TowerRepository repository;
	
	@Override
	public void save(Tower tower) throws ServiceException {
		this.repository.save(tower);
	}

	@Override
	public List<Tower> findAll() throws ServiceException {
		return (List<Tower>) this.repository.findAll();
	}

	@Override
	public Tower findById(Long id) throws ServiceException {
		return this.repository.findByIdentity(id);
	}

}
