package br.com.gp.inventory.domain.repository.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.gp.inventory.domain.entity.Inventory;
import br.com.gp.inventory.domain.repository.InventoryRepository;

@Repository("inventoryRepository")
@Transactional(propagation = Propagation.REQUIRED)
public class InventoryRepositoryImpl extends AbstractHibernateRepostirory<Inventory> implements InventoryRepository {

	private static final long serialVersionUID = 7825071112116663725L;

	public InventoryRepositoryImpl() {
		super(Inventory.class);
	}

}
