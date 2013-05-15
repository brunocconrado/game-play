package br.com.embracon.teamposition.domain.service;

import java.util.List;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.embracon.teamposition.domain.entity.SoldCode;
import br.com.embracon.teamposition.domain.enumeration.SoldCodeEnum;

public interface SoldCodeService {

	public List<SoldCode> listByType(SoldCodeEnum type) throws ServiceException;

	public SoldCode findById(Long diamant) throws ServiceException;

	public SoldCode findByCode(String code) throws ServiceException;

}
