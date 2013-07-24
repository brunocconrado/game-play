package br.com.gp.inventory.domain.bean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.VideoCard;
import br.com.gp.inventory.domain.service.VideoCardService;


@Controller("videoCardListBean")
@Scope(value = "session")
public class VideoCardListBean extends DefaultBean {

	@Autowired
	@Qualifier("videoCardService")
	private VideoCardService service;
	
	private List<VideoCard> viedoCards;
	
	private VideoCard videoCardSelected;
	
	public VideoCardListBean() {
		super("videoCardListBean");
	}
	
	@PostConstruct
	public void init() {
		try {
			this.viedoCards = service.findAll();
		} catch (ServiceException e) {
			fatalMessage("error.message.init", e);
			destroy("listProcessorBean");
		}
	}
	
	public void search() {
		init();
	}
	
	public void remove() {
		this.delete(this.service, "delete", 
				this.videoCardSelected, "Placa de Video", "Invent‡rio");
	}

	public List<VideoCard> getViedoCards() {
		return viedoCards;
	}

	public void setViedoCards(List<VideoCard> viedoCards) {
		this.viedoCards = viedoCards;
	}

	public VideoCard getVideoCardSelected() {
		return videoCardSelected;
	}

	public void setVideoCardSelected(VideoCard videoCardSelected) {
		this.videoCardSelected = videoCardSelected;
	}
	
}
