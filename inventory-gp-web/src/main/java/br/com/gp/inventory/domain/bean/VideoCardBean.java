package br.com.gp.inventory.domain.bean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Manufacturer;
import br.com.gp.inventory.domain.entity.Socket;
import br.com.gp.inventory.domain.entity.VideoCard;
import br.com.gp.inventory.domain.enumeration.CategoryEnum;
import br.com.gp.inventory.domain.service.ManufacturerService;
import br.com.gp.inventory.domain.service.VideoCardService;

@Controller("videoCardBean")
@Scope(value = "session")
public class VideoCardBean extends DefaultBean {

	public VideoCardBean() {
		super("videoCardBean");
	}
	
	@Autowired
	@Qualifier("videoCardService")
	private VideoCardService service;
	
	@Autowired
	@Qualifier("manufacturerService")
	private ManufacturerService manufactoryService;
	
	private List<Manufacturer> manufacturers;
	
	private List<Socket> sockets;
	
	private VideoCard videoCard;
	
	@PostConstruct
	public void init() {
		try {
			
			this.videoCard = new VideoCard();
			
			this.manufacturers = manufactoryService.findAllByCategory(CategoryEnum.VIDEO_CARD);
		} catch (ServiceException e) {
			errorMessage("error.search", "Placa de Video");
		}
	}
	
	public void save() {
		
		try {
			
			this.videoCard.setManufacturer(this.manufactoryService
							.findById(this.videoCard.getManufacturer().getId()));
			
			this.service.save(this.videoCard);
			
			successMessage("save.success", "Placa de Video");			
		} catch (ServiceException e) {
			errorMessage("error.search", "Placa de Video");
		} catch (Throwable e) {
			fatalMessage("fatal.error");
		}
	}
	
	public String linkRedirect() {
		destroy("videoCardBean");
		return "/pages/placa-video/lista";
	}
	
	public List<Manufacturer> getManufacturers() {
		return manufacturers;
	}

	public void setManufacturers(List<Manufacturer> manufacturers) {
		this.manufacturers = manufacturers;
	}
	
	public List<Socket> getSockets() {
		return sockets;
	}

	public void setSockets(List<Socket> sockets) {
		this.sockets = sockets;
	}

	public VideoCard getVideoCard() {
		return videoCard;
	}

	public void setVideoCard(VideoCard videoCard) {
		this.videoCard = videoCard;
	}

}
