package br.com.embracon.teamposition.domain.repository;

import java.util.List;

import br.com.embracon.teamposition.domain.entity.Menu;


public interface MenuRepository {
	

	public List<Menu> findAllActives();

	public Menu findRoot();
}
