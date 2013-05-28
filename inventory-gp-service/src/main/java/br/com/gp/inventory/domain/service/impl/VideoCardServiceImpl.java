package br.com.gp.inventory.domain.service.impl;

import java.util.List;

import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.VideoCard;
import br.com.gp.inventory.domain.repository.VideoCardRepository;
import br.com.gp.inventory.domain.service.VideoCardService;

@Component("videoCardService")
@Interceptors(value = {ServiceInteceptor.class})
public class VideoCardServiceImpl implements VideoCardService {
	
	@Autowired
	@Qualifier(value = "videoCardRepository")
	private VideoCardRepository repository;

	@Override
	public List<VideoCard> findAll() throws ServiceException {
		return (List<VideoCard>) repository.findAll();
	}

	@Override
	public void save(VideoCard videoCard) throws ServiceException {
		this.repository.save(videoCard);		
	}

	@Override
	public VideoCard findById(Long id) throws ServiceException {
		return this.repository.findByIdentity(id);
	}

}
