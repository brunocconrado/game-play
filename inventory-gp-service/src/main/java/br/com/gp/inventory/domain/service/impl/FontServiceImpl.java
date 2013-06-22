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
import br.com.gp.inventory.domain.entity.Font;
import br.com.gp.inventory.domain.entity.Manufacturer;
import br.com.gp.inventory.domain.entity.Potential;
import br.com.gp.inventory.domain.repository.FontRepository;
import br.com.gp.inventory.domain.service.FontService;
import br.com.gp.inventory.domain.service.ManufacturerService;
import br.com.gp.inventory.domain.service.PotentialService;
import br.com.gp.inventory.domain.util.StringUtils;

@Component("fontService")
@Interceptors(value = {ServiceInteceptor.class})
public class FontServiceImpl implements FontService {

	private static final int MANUFACTURER = 0;
	private static final int POTENTIAL = 1;
	private static final int NAME = 2;
	private static final int PRICE = 3;
	//private static final int PARCEL = 4;
	private static final int WATTS = 5;

	@Autowired
	@Qualifier(value = "fontRepository")
	private FontRepository repository;
	
	@Autowired
	@Qualifier(value = "manufacturerService")
	private ManufacturerService manufacturerService;
	
	@Autowired
	@Qualifier(value = "potentialService")
	private PotentialService potentialService;

	@Override
	public Font save(Font font) throws ServiceException {
		font = this.repository.save(font);
		font.setCode(StringUtils.formatString(font.getId(), 10, "F"));
		return this.repository.save(font);
	}

	@Override
	public List<Font> findAll() throws ServiceException {
		return (List<Font>) this.repository.findAll();
	}

	@Override
	public Font findById(Long id) throws ServiceException {
		return this.repository.findByIdentity(id);
	}
	
	@Override
	public void importFont(HSSFSheet sheet) {
		for(Iterator<Row> it = sheet.rowIterator(); it.hasNext(); ) {
			try {
				
				Row row = it.next();

				Manufacturer manufacturer = manufacturerService.findOrCreateByName(
						row.getCell(MANUFACTURER).getStringCellValue().trim());
				
				Potential potential = potentialService.findOrCreateByName(
						row.getCell(POTENTIAL).getStringCellValue().trim());
				
				Font font = new Font(manufacturer, potential);
				
				font.setTitle(row.getCell(NAME).getStringCellValue().trim());
				font.setPriceString(row.getCell(PRICE).getStringCellValue().trim());
				font.setWatts(row.getCell(WATTS).getStringCellValue().trim());
				font.setCode("0000000000");
				
				font = this.save(font);
				
				System.out.println(font.toString());
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
	}
}
