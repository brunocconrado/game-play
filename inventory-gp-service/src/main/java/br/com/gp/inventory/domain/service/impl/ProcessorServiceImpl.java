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
import br.com.gp.inventory.domain.entity.Motherboard;
import br.com.gp.inventory.domain.entity.Processor;
import br.com.gp.inventory.domain.entity.Socket;
import br.com.gp.inventory.domain.repository.MotherboardRepository;
import br.com.gp.inventory.domain.repository.ProcessorRepository;
import br.com.gp.inventory.domain.service.ManufacturerService;
import br.com.gp.inventory.domain.service.ProcessorService;
import br.com.gp.inventory.domain.service.SocketService;
import br.com.gp.inventory.domain.util.StringUtils;

@Component("processorService")
@Interceptors(value = {ServiceInteceptor.class})
public class ProcessorServiceImpl implements ProcessorService {

	private static final int SOCKET = 0;
	private static final int MANUFACTURER = 1;
	private static final int NAME = 2;
	private static final int PRICE = 3;
	//private static final int PARCEL = 4;
	private static final int WATTS = 5;

	@Autowired
	@Qualifier(value = "socketService")
	private SocketService socketService;

	@Autowired
	@Qualifier(value = "manufacturerService")
	private ManufacturerService manufacturerService;

	@Autowired
	@Qualifier(value = "processorRepository")
	private ProcessorRepository repository;

	@Autowired
	@Qualifier(value = "motherboardRepository")
	private MotherboardRepository motherboardRepository;

	@Override
	public List<Processor> findAll() throws ServiceException {
		return (List<Processor>) repository.findAll();
	}

	@Override
	public void save(Processor processor) throws ServiceException {
		this.repository.save(processor);	
	}

	@Override
	public List<Processor> findByMotherboard(Motherboard motherboard) throws ServiceException {
		return this.repository.findBySocket(motherboardRepository.findByIdentity(motherboard.getId()).getSocket());
	}

	@Override
	public Processor findById(Long id) throws ServiceException {
		return this.repository.findByIdentity(id);
	}

	@Override
	public void importProcessor(HSSFSheet sheet) {
		for(Iterator<Row> it = sheet.rowIterator(); it.hasNext(); ) {
			try {
				
				Row row = it.next();
				Socket socket = socketService.findOrCreateByName(
						row.getCell(SOCKET).getStringCellValue().trim());
				
				Manufacturer manufacturer = manufacturerService.findOrCreateByName(
								row.getCell(MANUFACTURER).getStringCellValue().trim());
				
				Processor processor = new Processor(socket, manufacturer);
				processor.setTitle(row.getCell(NAME).getStringCellValue().trim());
				processor.setPriceString(row.getCell(PRICE).getStringCellValue().trim());
				processor.setWatts(row.getCell(WATTS).getStringCellValue().trim());
				processor.setCode("0000000000");
				
				processor = this.repository.save(processor);
				processor.setCode(StringUtils.formatString(processor.getId(), 10, "P"));
				processor = this.repository.save(processor);
				
				System.out.println(processor.toString());
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
	}
}
