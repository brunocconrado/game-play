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
import br.com.gp.inventory.domain.entity.HardDisk;
import br.com.gp.inventory.domain.entity.Manufacturer;
import br.com.gp.inventory.domain.repository.HardDiskRepository;
import br.com.gp.inventory.domain.service.HardDiskService;
import br.com.gp.inventory.domain.service.ManufacturerService;
import br.com.gp.inventory.domain.util.StringUtils;

@Component("hardDiskService")
@Interceptors(value = {ServiceInteceptor.class})
public class HardDiskServiceImpl implements HardDiskService {

	private static final int MANUFACTURER = 0;
	private static final int TYPE = 1;
	private static final int CAPACITY = 2;
	private static final int NAME = 3;
	private static final int PRICE = 4;
	//private static final int PARCEL = 5;
	private static final int WATTS = 6;
	
	@Autowired
	@Qualifier(value = "hardDiskRepository")
	private HardDiskRepository repository;
	
	@Autowired
	@Qualifier(value = "manufacturerService")
	private ManufacturerService manufacturerService;

	@Override
	public HardDisk save(HardDisk hardDisk) throws ServiceException {
		return this.repository.save(hardDisk);
	}

	@Override
	public List<HardDisk> findAll() throws ServiceException {
		return (List<HardDisk>) this.repository.findAll();
	}

	@Override
	public HardDisk findById(Long id) throws ServiceException {
		return this.repository.findByIdentity(id);
	}
	
	@Override
	public void importHardDisk(HSSFSheet sheet) {
		for(Iterator<Row> it = sheet.rowIterator(); it.hasNext(); ) {
			try {
				
				Row row = it.next();

				Manufacturer manufacturer = manufacturerService.findOrCreateByName(
						row.getCell(MANUFACTURER).getStringCellValue().trim());
				
				HardDisk hardDisk = new HardDisk(manufacturer);
				
				hardDisk.setCapacity(row.getCell(CAPACITY).getStringCellValue().trim());
				hardDisk.setTitle(row.getCell(NAME).getStringCellValue().trim());
				hardDisk.setSsd(row.getCell(TYPE).getStringCellValue().trim().equals("SIM"));
				hardDisk.setPriceString(row.getCell(PRICE).getStringCellValue().trim());
				hardDisk.setWatts(row.getCell(WATTS).getStringCellValue().trim());
				hardDisk.setCode("0000000000");
				
				hardDisk = this.save(hardDisk);
				hardDisk.setCode(StringUtils.formatString(hardDisk.getId(), 10, "HD"));
				hardDisk = this.save(hardDisk);
				
				System.out.println(hardDisk.toString());
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
	}
	
}
