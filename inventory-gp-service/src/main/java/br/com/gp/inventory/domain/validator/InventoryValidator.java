package br.com.gp.inventory.domain.validator;

import br.com.embracon.j4e.util.Objects;
import br.com.embracon.j4e.validation.LocalizableReason;
import br.com.embracon.j4e.validation.ValidationResult;
import br.com.embracon.j4e.validation.Validator;
import br.com.gp.inventory.domain.entity.Inventory;

public class InventoryValidator  implements Validator<Inventory> {

	@Override
	public ValidationResult validate(Inventory inventory) {

		ValidationResult result = new ValidationResult();
		if(Objects.isNull(inventory.getQtdDrive()) || inventory.getQtdDrive() < 1) {
			result.addReason(new LocalizableReason("validation.quantity.drive"));
		}
		
		if(Objects.isNull(inventory.getQtdHardDisk()) || inventory.getQtdHardDisk() < 1) {
			result.addReason(new LocalizableReason("validation.quantity.hd"));
		}
		
		if(Objects.isNull(inventory.getQtdMemory()) || inventory.getQtdMemory() < 1) {
			result.addReason(new LocalizableReason("validation.quantity.memory"));
		}
		
		return result;
	}

}
