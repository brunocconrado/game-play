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
import br.com.gp.inventory.domain.entity.Frequency;
import br.com.gp.inventory.domain.entity.Manufacturer;
import br.com.gp.inventory.domain.entity.Memory;
import br.com.gp.inventory.domain.repository.MemoryRepository;
import br.com.gp.inventory.domain.service.FrequencyService;
import br.com.gp.inventory.domain.service.ManufacturerService;
import br.com.gp.inventory.domain.service.MemoryService;
import br.com.gp.inventory.domain.util.StringUtils;

@Component("memoryService")
@Interceptors(value = {ServiceInteceptor.class})
public class MemoryServiceImpl implements MemoryService {
	
	private static final int MANUFACTURER = 0;
	private static final int CAPACITY = 1;
	private static final int FREQUENCY = 2;
	private static final int NAME = 3;
	private static final int PRICE = 4;
	//private static final int PARCEL = 5;
	private static final int WATTS = 6;
	
	
	@Autowired
	@Qualifier(value = "memoryRepository")
	private MemoryRepository reposiroty;
	
	@Autowired
	@Qualifier(value = "manufacturerService")
	private ManufacturerService manufacturerService;
	
	@Autowired
	@Qualifier(value = "frequencyService")
	private FrequencyService frequencyService;

	@Override
	public Memory save(Memory memory) throws ServiceException {
		return this.reposiroty.save(memory);
	}

	@Override
	public List<Memory> findAll() throws ServiceException {
		return (List<Memory>) this.reposiroty.findAll();
	}

	@Override
	public Memory findById(Long id) throws ServiceException {
		return this.reposiroty.findByIdentity(id);
	}
	
	@Override
	public void importMemory(HSSFSheet sheet) {
		for(Iterator<Row> it = sheet.rowIterator(); it.hasNext(); ) {
			try {
				
				Row row = it.next();

				Manufacturer manufacturer = manufacturerService.findOrCreateByName(
						row.getCell(MANUFACTURER).getStringCellValue().trim());
				
				Frequency frequency = frequencyService.findOrCreateByName(
						row.getCell(FREQUENCY).getStringCellValue().trim());
				
				Memory memory = new Memory(manufacturer, frequency);
				
				memory.setCapacity(row.getCell(CAPACITY).getStringCellValue().trim());
				memory.setTitle(row.getCell(NAME).getStringCellValue().trim());
				memory.setPriceString(row.getCell(PRICE).getStringCellValue().trim());
				memory.setWatts(row.getCell(WATTS).getStringCellValue().trim());
				memory.setCode("0000000000");
				
				memory = this.save(memory);
				memory.setCode(StringUtils.formatString(memory.getId(), 10, "M"));
				memory = this.save(memory);
				
				System.out.println(memory.toString());
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
	}
}
