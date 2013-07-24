package br.com.gp.inventory.domain.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.VideoCard;

public interface VideoCardService {
	
	public List<VideoCard> findAll() throws ServiceException;

	public VideoCard save(VideoCard videoCard) throws ServiceException;
	
	public void delete(VideoCard videoCard) throws ServiceException;

	public VideoCard findById(Long id) throws ServiceException;

	public void importVideoCard(Sheet sheet) throws ServiceException;

}
