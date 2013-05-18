package br.com.gp.inventory.domain.repository;

import java.util.List;

import br.com.gp.inventory.domain.entity.Menu;


public interface MenuRepository {
	

	public List<Menu> findAllActives();

	public Menu findRoot();
}
