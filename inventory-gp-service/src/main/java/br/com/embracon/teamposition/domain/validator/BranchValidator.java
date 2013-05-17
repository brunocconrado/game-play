package br.com.embracon.teamposition.domain.validator;

import br.com.embracon.j4e.validation.LocalizableReason;
import br.com.embracon.j4e.validation.ValidationResult;
import br.com.embracon.j4e.validation.Validator;
import br.com.embracon.teamposition.domain.entity.tmp.Branch;
import br.com.embracon.teamposition.domain.entity.tmp.Collaborator;
import br.com.embracon.teamposition.domain.entity.tmp.Regional;
import br.com.embracon.teamposition.domain.repository.BranchRepository;

public class BranchValidator  implements Validator<Branch> {

	private BranchRepository repository;
	
	public BranchValidator(BranchRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public ValidationResult validate(Branch branch) {
		
		ValidationResult result = new ValidationResult();
		
		if((branch.getCode() == null || branch.getCode().isEmpty())
				|| (branch.getName() == null || branch.getName().isEmpty())) {
			result.addReason(new LocalizableReason("validation.required", "Filial"));
		}
		
		Collaborator regional = branch.getRegional();
		
		if(regional == null || regional.getRegistry() == null || 
				(regional.getCpf() == null || regional.getCpf().isEmpty())) {
			result.addReason(new LocalizableReason("validation.required", "Regional"));
		}
		
		Collaborator manager = branch.getManager();
		if(manager == null || manager.getRegistry() == null || 
				(manager.getCpf() == null || manager.getCpf().isEmpty())) {
			result.addReason(new LocalizableReason("validation.required", "Gerente"));
		}
		
		Regional aRegional = branch.getaRegional();
		if(aRegional == null || (aRegional.getCode() == null || aRegional.getCode().isEmpty())
				|| (aRegional.getName() == null || aRegional.getName().isEmpty())) {
			result.addReason(new LocalizableReason("validation.required", "a Regional"));
		}
		
		if(repository.existFilial(branch)) {
			result.addReason(new LocalizableReason("validation.exist", "Filial", "Codigo", branch.getCode()));
		}
		
		return result;
	}

}
