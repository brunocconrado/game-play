package br.com.embracon.teamposition.domain.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.embracon.j4e.i18n.Messages;
import br.com.embracon.j4e.validation.LocalizableReason;
import br.com.embracon.j4e.validation.ValidationResult;
import br.com.embracon.j4e.validation.Validator;

public class EmailValidator implements Validator<CharSequence> {

	private Pattern pattern;

	private static final String EMAIL_PATTERN = Messages.getMessage("pattern.email");

	public EmailValidator(){
		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	/**
	 * Validate hex with regular expression
	 * @param hex hex for validation
	 * @return true valid hex, false invalid hex
	 */
	@Override
	public ValidationResult validate(CharSequence email) {

		ValidationResult result = new ValidationResult();
		
		if(!pattern.matcher(email).matches()){
			result.addReason(new LocalizableReason("validation.email", email));
		}

		return result;
	}

}
