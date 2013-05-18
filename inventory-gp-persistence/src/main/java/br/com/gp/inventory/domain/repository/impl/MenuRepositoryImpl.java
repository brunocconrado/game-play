package br.com.gp.inventory.domain.repository.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.gp.inventory.domain.entity.Menu;
import br.com.gp.inventory.domain.repository.MenuRepository;

@Repository("menuRepository")
@SuppressWarnings("unchecked")
@Transactional(propagation = Propagation.REQUIRED)
public class MenuRepositoryImpl extends AbstractHibernateRepostirory<Menu> implements MenuRepository {
	
	
	private static final long serialVersionUID = 8597406717799391892L;

	public MenuRepositoryImpl() {
		super(Menu.class);
	}
			
	public List<Menu> findAllActives() {
		return createCriteria().list();
	}

	@Override
	public Menu findRoot() {
		
		Menu menu = findBy(createCriteria().add(Restrictions.isNull("parent")));
		Hibernate.initialize(menu.getChildren());
		
		return menu;
	}
}
