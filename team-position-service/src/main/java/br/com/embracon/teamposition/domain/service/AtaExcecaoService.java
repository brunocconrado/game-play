package br.com.embracon.teamposition.domain.service;

import java.util.Set;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.embracon.teamposition.domain.entity.AtaExcecao;

public interface AtaExcecaoService {

	public void delete(Set<AtaExcecao> exceptions) throws ServiceException;

	public void save(AtaExcecao excecao) throws ServiceException;

}
