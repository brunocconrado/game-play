package br.com.gp.inventory.domain.service.impl;

import java.util.List;

import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.embracon.j4e.validation.ValidationException;
import br.com.embracon.j4e.validation.ValidationResult;
import br.com.gp.inventory.domain.entity.Inventory;
import br.com.gp.inventory.domain.repository.InventoryRepository;
import br.com.gp.inventory.domain.service.InventoryService;
import br.com.gp.inventory.domain.util.StringUtils;
import br.com.gp.inventory.domain.validator.InventoryValidator;

@Component("inventoryService")
@Interceptors(value = {ServiceInteceptor.class})
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	@Qualifier(value = "inventoryRepository")
	private InventoryRepository repository;
	
	@Override
	public void save(Inventory inventory) throws ServiceException {
		
		InventoryValidator validator = new InventoryValidator();
		ValidationResult result = validator.validate(inventory);
		if(!result.isValid()) {
			throw new ValidationException(result);
		}
		
		inventory.sumTotal();
		
		inventory = this.repository.save(inventory);
		inventory.setCode(StringUtils.formatString(inventory.getId(), 10, "INV"));
		inventory = this.repository.save(inventory);
		
	}

	@Override
	public List<Inventory> findAll() throws ServiceException {
		return (List<Inventory>) this.repository.findAll();
	}

}
