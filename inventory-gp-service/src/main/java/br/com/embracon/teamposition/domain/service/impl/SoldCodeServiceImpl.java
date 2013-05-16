package br.com.embracon.teamposition.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.embracon.j4e.domain.repository.RepositoryException;
import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.embracon.teamposition.domain.entity.SoldCode;
import br.com.embracon.teamposition.domain.enumeration.SoldCodeEnum;
import br.com.embracon.teamposition.domain.repository.SoldCodeRepository;
import br.com.embracon.teamposition.domain.service.SoldCodeService;

@Component("soldCodeService")
public class SoldCodeServiceImpl implements SoldCodeService {

	@Autowired
	@Qualifier(value = "soldCodeRepository")
	private SoldCodeRepository repository;
	
	@Override
	public List<SoldCode> listByType(SoldCodeEnum type) throws ServiceException {
		try {
			return this.repository.listByType(type);
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public SoldCode findById(Long diamant) throws ServiceException {
		try {
			return this.repository.findByIdentity(diamant);
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public SoldCode findByCode(String code) throws ServiceException {
		try {
			return this.repository.findByCode(code);
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

}
