package br.com.embracon.teamposition.domain.repository;

import java.util.List;

import br.com.embracon.teamposition.domain.entity.tmp.Branch;
import br.com.embracon.teamposition.domain.search.BranchSearch;

public interface BranchRepository extends br.com.embracon.j4e.domain.repository.Repository<Branch> {

	public List<Branch> findAllActive() ;

	public List<Branch> searchBy(BranchSearch search);

	public Branch findByCode(String code);

	public boolean existFilial(Branch branch);

	public Branch searchByCollaboratorRegistry(Integer registry);

}
