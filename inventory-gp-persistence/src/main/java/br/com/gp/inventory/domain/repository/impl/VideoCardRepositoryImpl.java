package br.com.gp.inventory.domain.repository.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.gp.inventory.domain.entity.VideoCard;
import br.com.gp.inventory.domain.repository.VideoCardRepository;

@Repository("videoCardRepository")
@Transactional(propagation = Propagation.REQUIRED)
public class VideoCardRepositoryImpl extends AbstractHibernateRepostirory<VideoCard> implements VideoCardRepository {

	private static final long serialVersionUID = -4726894399323583967L;

	public VideoCardRepositoryImpl() {
		super(VideoCard.class);
	}

}
