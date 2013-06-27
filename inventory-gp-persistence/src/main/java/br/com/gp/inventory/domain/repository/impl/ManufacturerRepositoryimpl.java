package br.com.gp.inventory.domain.repository.impl;

import java.util.Collection;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.gp.inventory.domain.entity.Manufacturer;
import br.com.gp.inventory.domain.enumeration.CategoryEnum;
import br.com.gp.inventory.domain.repository.ManufacturerRepository;

@SuppressWarnings("unchecked")
@Repository("manufacturerRepository")
@Transactional(propagation = Propagation.REQUIRED)
public class ManufacturerRepositoryimpl extends AbstractHibernateRepostirory<Manufacturer> implements ManufacturerRepository {

	private static final long serialVersionUID = -8886713418391318485L;

	public ManufacturerRepositoryimpl() {
		super(Manufacturer.class);
	}
	
	@Override
	public Collection<Manufacturer> findByCategory(CategoryEnum category) {
		return this.createCriteria().add(Restrictions.eq("category.id", category.value())).list();
	}

	@Override
	public Manufacturer findByNameAndCategory(String name, CategoryEnum category) {
		return (Manufacturer) this.createCriteria()
				.add(Restrictions.eq("category.id", category.value()))
				.add(Restrictions.eq("name", name))
				.uniqueResult();
	}

}
