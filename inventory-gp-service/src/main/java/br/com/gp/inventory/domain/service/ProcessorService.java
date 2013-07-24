package br.com.gp.inventory.domain.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;

import br.com.embracon.j4e.services.exception.ServiceException;
<<<<<<< HEAD
import br.com.gp.inventory.domain.entity.Drive;
=======
import br.com.gp.inventory.domain.entity.Motherboard;
>>>>>>> ccdd4fa67d62621782d619c6692d92cf3cbe985b
import br.com.gp.inventory.domain.entity.Processor;

public interface ProcessorService {

	public List<Processor> findAll() throws ServiceException;

	public Processor save(Processor processor) throws ServiceException;

	public List<Processor> findByMotherboard(Motherboard motherboard) throws ServiceException;

	public Processor findById(Long id) throws ServiceException;

	void importProcessor(Sheet sheet) throws ServiceException;
	
	public void delete(Processor processor) throws ServiceException;
	
}
