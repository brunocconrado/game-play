package br.com.embracon.teamposition.domain.service;

import java.util.List;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.embracon.teamposition.domain.entity.Situation;
import br.com.embracon.teamposition.domain.enumeration.SituationEnum;

public interface SituationService {
	
	public List<Situation> listSellerSituaions() throws ServiceException;
	
	public List<Situation> listSupervisorSituaions() throws ServiceException;

	public Situation findBy(SituationEnum active) throws ServiceException;

	public Situation findById(Long situationId) throws ServiceException;

}
