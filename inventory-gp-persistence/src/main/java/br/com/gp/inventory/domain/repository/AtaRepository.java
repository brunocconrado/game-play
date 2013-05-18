package br.com.gp.inventory.domain.repository;

import java.util.Date;
import java.util.List;

import br.com.embracon.j4e.domain.repository.Repository;
import br.com.gp.inventory.domain.entity.tmp.Ata;
import br.com.gp.inventory.domain.search.AtaSearch;


public interface AtaRepository extends Repository<Ata> {

	public List<Ata> searchBy(AtaSearch search);

	public boolean existDateInteval(Long id, Date start, Date end, String fieldStart, String fieldEnd);

	public boolean hasTeamSeller(Ata ata);
	
	public Ata findByDate(Date date);

	public Ata findByRegistryDate(Date date);
	
	public Ata findByRegistryDate(Date date, boolean start);

	public Ata findByMonthAndYear(Integer month, Integer year);
}