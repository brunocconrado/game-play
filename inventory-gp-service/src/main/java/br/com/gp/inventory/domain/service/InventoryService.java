package br.com.gp.inventory.domain.service;

import java.util.List;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Inventory;

public interface InventoryService {

	public void save(Inventory inventory) throws ServiceException;

	public List<Inventory> findAll() throws ServiceException;

	public String createHtmlText(Inventory inventoryReport);

}
