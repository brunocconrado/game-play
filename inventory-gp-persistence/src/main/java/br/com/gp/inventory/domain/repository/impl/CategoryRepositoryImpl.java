package br.com.gp.inventory.domain.repository.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.gp.inventory.domain.entity.Category;
import br.com.gp.inventory.domain.repository.CategoryRepository;

@Repository("categoryRepository")
@Transactional(propagation = Propagation.REQUIRED)
public class CategoryRepositoryImpl extends AbstractHibernateRepostirory<Category> implements CategoryRepository {

	private static final long serialVersionUID = -958707830732094610L;

	public CategoryRepositoryImpl() {
		super(Category.class);
	}
	
}
