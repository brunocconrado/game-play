package br.com.gp.inventory.domain.service;

import java.util.List;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.HardDisk;

public interface HardDiskService {

	public void save(HardDisk hardDisk) throws ServiceException;

	public List<HardDisk> findAll() throws ServiceException;

}
