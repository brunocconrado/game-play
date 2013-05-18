package br.com.embracon.teamposition.domain.repository.impl;

import java.util.Collection;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.embracon.j4e.domain.repository.AbstractRepository;
import br.com.embracon.teamposition.domain.entity.Manufacturer;
import br.com.embracon.teamposition.domain.repository.ManufacturerRepository;

@Repository("manufacturerRepository")
@Transactional(propagation = Propagation.REQUIRED)
public class ManufacturerRepositoryimpl extends AbstractRepository<Manufacturer> implements ManufacturerRepository {

	public ManufacturerRepositoryimpl() {
		super(Manufacturer.class);
	}

	@Override
	public Collection<Manufacturer> findByCategory(Long categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

}
