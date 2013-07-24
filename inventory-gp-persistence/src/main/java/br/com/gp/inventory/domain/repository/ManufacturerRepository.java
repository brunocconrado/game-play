package br.com.gp.inventory.domain.repository;

import java.util.Collection;

import br.com.embracon.j4e.domain.repository.Repository;
import br.com.gp.inventory.domain.entity.Manufacturer;
import br.com.gp.inventory.domain.enumeration.CategoryEnum;

public interface ManufacturerRepository extends Repository<Manufacturer> {

	public Collection<Manufacturer> findByCategory(CategoryEnum category);

	public Manufacturer findByNameAndCategory(String name, CategoryEnum category);

}
