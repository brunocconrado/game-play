package br.com.gp.inventory.domain.validator;

import br.com.embracon.j4e.validation.LocalizableReason;
import br.com.embracon.j4e.validation.ValidationResult;
import br.com.embracon.j4e.validation.Validator;
import br.com.gp.inventory.domain.search.CollaboratorSearch;

public class CollaboratorSearchValidator implements Validator<CollaboratorSearch> {

	private boolean isValidationEmail;
	
	public CollaboratorSearchValidator(boolean isValidationEmail) {
		this.isValidationEmail = isValidationEmail;
	}
	
	public CollaboratorSearchValidator() {
		this.isValidationEmail = Boolean.TRUE;
	}
	
	@Override
	public ValidationResult validate(CollaboratorSearch search) {
		
		ValidationResult result = new ValidationResult();
		
		if(this.isValidationEmail) {
			EmailValidator emailValidator = new EmailValidator();
			result = emailValidator
							.validate(search.getEmail());
		}
						
		if(search.getCpf().isEmpty() && (search.getRegistry() == null || search.getRegistry().isEmpty()) && 
				(search.getEmail().isEmpty() || !result.isValid())) {
			result.addReason(new LocalizableReason("validation.search.fields.empty"));
		}
		
		return result;
	}

}
