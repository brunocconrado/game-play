package br.com.gp.inventory.domain.repository;

import java.util.Collection;

import br.com.embracon.j4e.domain.repository.Repository;
import br.com.gp.inventory.domain.entity.Manufacturer;

public interface ManufacturerRepository extends Repository<Manufacturer> {

	public Collection<Manufacturer> findByCategory(Long categoryId);

}
