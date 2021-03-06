package br.com.gp.inventory.domain.service.impl;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import javax.interceptor.Interceptors;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.embracon.j4e.services.exception.ServiceException;
<<<<<<< HEAD
import br.com.gp.inventory.domain.entity.Drive;
=======
import br.com.gp.inventory.domain.entity.Manufacturer;
>>>>>>> ccdd4fa67d62621782d619c6692d92cf3cbe985b
import br.com.gp.inventory.domain.entity.VideoCard;
import br.com.gp.inventory.domain.enumeration.CategoryEnum;
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
		videoCard = this.repository.save(videoCard);
		videoCard.setCode(StringUtils.formatString(videoCard.getId(), 10, "PV"));
		return this.repository.save(videoCard);		
	}

	@Override
	public VideoCard findById(Long id) throws ServiceException {
		return this.repository.findByIdentity(id);
	}
	
	@Override
	public void delete(VideoCard videoCard) throws ServiceException {
		this.repository.delete(videoCard);
	}

	@Override
	public void importVideoCard(Sheet sheet) {
		boolean isFirst = Boolean.TRUE;
		for(Iterator<Row> it = sheet.rowIterator(); it.hasNext(); ) {
			try {
				
				Row row = it.next();
				if(isFirst) {
					isFirst = Boolean.FALSE;
					continue;
				}
				
				Manufacturer manufacturer = manufacturerService.findOrCreateByNameAndCategory(
						row.getCell(MANUFACTURER).getStringCellValue().trim(),
						CategoryEnum.VIDEO_CARD
				);
				
				VideoCard videoCard = new VideoCard(manufacturer);
				videoCard.setTitle(row.getCell(NAME).getStringCellValue().trim());
				videoCard.setPrice(BigDecimal.valueOf(row.getCell(PRICE).getNumericCellValue()));
				videoCard.setWatts(String.valueOf(row.getCell(WATTS).getNumericCellValue()));
				videoCard.setCode("0000000000");
				
				videoCard = this.save(videoCard);
				
				System.out.println(videoCard.toString());
			} catch (ServiceException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
