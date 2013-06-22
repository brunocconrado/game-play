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
import br.com.gp.inventory.domain.entity.Drive;
import br.com.gp.inventory.domain.entity.Manufacturer;
import br.com.gp.inventory.domain.repository.DriveRepository;
import br.com.gp.inventory.domain.service.DriveService;
import br.com.gp.inventory.domain.service.ManufacturerService;
import br.com.gp.inventory.domain.util.StringUtils;

@Component("driveService")
@Interceptors(value = {ServiceInteceptor.class})
public class DriveServiceImpl implements DriveService {

	private static final int MANUFACTURER = 0;
	private static final int NAME = 1;
	private static final int PRICE = 2;
	//private static final int PARCEL = 3;
	private static final int WATTS = 4;
	
	@Autowired
	@Qualifier(value = "driveRepository")
	private DriveRepository repository;
	
	@Autowired
	@Qualifier(value = "manufacturerService")
	private ManufacturerService manufacturerService;	
	
	@Override
	public Drive save(Drive drive) throws ServiceException {
		drive = this.repository.save(drive);
		drive.setCode(StringUtils.formatString(drive.getId(), 10, "D"));
		return this.repository.save(drive);
	}

	@Override
	public List<Drive> findAll() throws ServiceException {
		return (List<Drive>) this.repository.findAll();
	}

	@Override
	public Drive findById(Long id) throws ServiceException {
		return this.repository.findByIdentity(id);
	}
	
	@Override
	public void importDriver(HSSFSheet sheet) {
		for(Iterator<Row> it = sheet.rowIterator(); it.hasNext(); ) {
			try {
				
				Row row = it.next();

				Manufacturer manufacturer = manufacturerService.findOrCreateByName(
						row.getCell(MANUFACTURER).getStringCellValue().trim());
				
				Drive drive = new Drive(manufacturer);
				
				drive.setName(row.getCell(NAME).getStringCellValue().trim());
				drive.setPriceString(row.getCell(PRICE).getStringCellValue().trim());
				drive.setWatts(row.getCell(WATTS).getStringCellValue().trim());
				drive.setCode("0000000000");
				
				drive = this.save(drive);
				
				System.out.println(drive.toString());
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
	}

}
