package br.com.gp.inventory.domain.service;

import java.util.List;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.VideoCard;

public interface VideoCardService {
	
	public List<VideoCard> findAll() throws ServiceException;

	public void save(VideoCard videoCard) throws ServiceException;

	public VideoCard findById(Long id) throws ServiceException;

}
