package br.com.gp.inventory.domain.service;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Font;

public interface FontService {
	
	public Font save(Font font) throws ServiceException;

	public List<Font> findAll() throws ServiceException;

	public Font findById(Long id) throws ServiceException;

	void importFont(HSSFSheet sheet) throws ServiceException;

}
