package br.com.gp.inventory.domain.service.impl;

import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Inventory;
import br.com.gp.inventory.domain.repository.InventoryRepository;
import br.com.gp.inventory.domain.service.InventoryService;

@Component("inventoryService")
@Interceptors(value = {ServiceInteceptor.class})
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	@Qualifier(value = "inventoryRepository")
	private InventoryRepository repository;
	
	@Override
	public void save(Inventory inventory) throws ServiceException {
		
	}

}
