package br.com.embracon.teamposition.domain.validator;

import br.com.embracon.j4e.validation.LocalizableReason;
import br.com.embracon.j4e.validation.ValidationResult;
import br.com.embracon.j4e.validation.Validator;
import br.com.embracon.teamposition.domain.entity.tmp.Ata;
import br.com.embracon.teamposition.domain.repository.AtaRepository;

public class AtaValidator implements Validator<Ata> {

	private AtaRepository repository;

	public AtaValidator(AtaRepository repository) {
		this.repository = repository;
	}

	@Override
	public ValidationResult validate(Ata ata) {

		ValidationResult resultado = new ValidationResult();

		if(ata.getStart().compareTo(ata.getEnd()) > 0) {
			resultado.addReason(new LocalizableReason(
					"validation.date.start.more.than.end"));
		}

		if(ata.getRegistryStart().compareTo(ata.getRegistryEnd()) > 0) {
			resultado.addReason(new LocalizableReason(
					"validation.date.start.more.than.end"));
		}

		if(ata.getStart().compareTo(ata.getRegistryStart()) <= 0) {
			resultado.addReason(new LocalizableReason(
					"validation.date.start.ata.less.than.start.registry"));
		}

		if(ata.getEnd().compareTo(ata.getRegistryStart()) <= 0) {
			resultado.addReason(new LocalizableReason(
					"validation.date.end.ata.less.than.start.registry"));
		}

		if(this.repository.existDateInteval(ata.getId(), 
					ata.getStart(), ata.getEnd(), "start", "end")) {
			resultado.addReason(new LocalizableReason(
					"validation.date.ata.between.other.interval"));
		}

		if(this.repository.existDateInteval(ata.getId(), 
					ata.getRegistryStart(), ata.getRegistryEnd(), "registryStart", "registryEnd")) {
			resultado.addReason(new LocalizableReason(
					"validation.date.ata.registry.between.other.interval"));
		}

		return resultado;
	}

}
