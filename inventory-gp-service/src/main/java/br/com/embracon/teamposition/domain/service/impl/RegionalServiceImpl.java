package br.com.embracon.teamposition.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.embracon.j4e.domain.repository.RepositoryException;
import br.com.embracon.j4e.reflection.ReflectionUtils;
import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.embracon.j4e.validation.LocalizableReason;
import br.com.embracon.j4e.validation.ValidationException;
import br.com.embracon.j4e.validation.ValidationResult;
import br.com.embracon.teamposition.domain.entity.Regional;
import br.com.embracon.teamposition.domain.repository.RegionalRepository;
import br.com.embracon.teamposition.domain.service.RegionalService;
import br.com.embracon.wsentity.domain.entity.teamposition.RegionalInfo;
import br.com.embracon.wsteamposition.ws.util.WSFactory;

@Component("regionalService")
public class RegionalServiceImpl implements RegionalService {

	@Autowired
	@Qualifier(value = "regionalRepository")
	private RegionalRepository repository;
	
	@Override
	public Regional findRegional(String aRegionalCode) throws ServiceException {

		try {

			br.com.embracon.wsembraconvo.teamposition.Regional regional = 
							new br.com.embracon.wsembraconvo.teamposition.Regional();
			regional.setCode(aRegionalCode);
			regional.setSystemCallIdentificator("009");
			
			RegionalInfo result = WSFactory.getService().findRegional(regional);
			ValidationResult validationResult = new ValidationResult();
			if(result.getProcessStatus() == 99) {
				validationResult.addReason(new LocalizableReason(
						"validation.not.found", "Regional", "Codigo", 
						aRegionalCode == null ? " - " : aRegionalCode, "SCE"));
				throw new ValidationException(validationResult);
			}
			
			Regional found = repository.findByCode(aRegionalCode);
			if(found == null) {
				found = new Regional();
			}
			
			return ReflectionUtils.copySameFields(result, found);
		} catch (ValidationException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public void save(Regional regional) throws ServiceException {
		
		try {
			this.repository.save(regional);
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}
	
}
