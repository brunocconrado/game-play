package br.com.embracon.teamposition.domain.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.embracon.j4e.domain.repository.RepositoryException;
import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.embracon.j4e.util.DateUtils;
import br.com.embracon.teamposition.domain.entity.AtaExcecao;
import br.com.embracon.teamposition.domain.repository.AtaExcecaoRepository;
import br.com.embracon.teamposition.domain.service.AtaExcecaoService;

@Component("ataExcecaoService")
public class AtaExcecaoServiceImpl implements AtaExcecaoService {

	@Autowired
	@Qualifier("ataExcecaoRepository")
	private AtaExcecaoRepository repository;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Set<AtaExcecao> exceptions) throws ServiceException {
		
		try {
			repository.delete(exceptions);
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}	
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(AtaExcecao excecao) throws ServiceException {

		try {
			
			excecao.setRecord(DateUtils.getDate());
			repository.save(excecao);
			
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}	
	}

}
