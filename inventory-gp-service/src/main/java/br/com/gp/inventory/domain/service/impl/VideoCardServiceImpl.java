package br.com.gp.inventory.domain.service.impl;

import java.util.Iterator;
import java.util.List;

import javax.interceptor.Interceptors;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Manufacturer;
import br.com.gp.inventory.domain.entity.VideoCard;
import br.com.gp.inventory.domain.repository.VideoCardRepository;
import br.com.gp.inventory.domain.service.ManufacturerService;
import br.com.gp.inventory.domain.service.VideoCardService;
import br.com.gp.inventory.domain.util.StringUtils;

@Component("videoCardService")
@Interceptors(value = {ServiceInteceptor.class})
public class VideoCardServiceImpl implements VideoCardService {
	
	private static final int MANUFACTURER = 0;
	private static final int NAME = 1;
	private static final int PRICE = 2;
	//private static final int PARCEL = 3;
	private static final int WATTS = 4;
	
	@Autowired
	@Qualifier(value = "videoCardRepository")
	private VideoCardRepository repository;
	
	@Autowired
	@Qualifier(value = "manufacturerService")
	private ManufacturerService manufacturerService;

	@Override
	public List<VideoCard> findAll() throws ServiceException {
		return (List<VideoCard>) repository.findAll();
	}

	@Override
	public VideoCard save(VideoCard videoCard) throws ServiceException {
		return this.repository.save(videoCard);		
	}

	@Override
	public VideoCard findById(Long id) throws ServiceException {
		return this.repository.findByIdentity(id);
	}

	@Override
	public void importVideoCard(HSSFSheet sheet) {
		for(Iterator<Row> it = sheet.rowIterator(); it.hasNext(); ) {
			try {
				
				Row row = it.next();

				Manufacturer manufacturer = manufacturerService.findOrCreateByName(
						row.getCell(MANUFACTURER).getStringCellValue().trim());
				
				VideoCard videoCard = new VideoCard(manufacturer);
				videoCard.setTitle(row.getCell(NAME).getStringCellValue().trim());
				videoCard.setPriceString(row.getCell(PRICE).getStringCellValue().trim());
				videoCard.setWatts(row.getCell(WATTS).getStringCellValue().trim());
				videoCard.setCode("0000000000");
				
				videoCard = this.save(videoCard);
				videoCard.setCode(StringUtils.formatString(videoCard.getId(), 10, "PV"));
				videoCard = this.save(videoCard);
				
				System.out.println(videoCard.toString());
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
	}
}
