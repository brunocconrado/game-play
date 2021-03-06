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
import br.com.gp.inventory.domain.entity.Motherboard;
>>>>>>> ccdd4fa67d62621782d619c6692d92cf3cbe985b
import br.com.gp.inventory.domain.entity.Processor;
import br.com.gp.inventory.domain.entity.Socket;
import br.com.gp.inventory.domain.enumeration.CategoryEnum;
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
	public Processor save(Processor processor) throws ServiceException {
		processor = this.repository.save(processor);
		processor.setCode(StringUtils.formatString(processor.getId(), 10, "P"));
		return this.repository.save(processor);	
	}
	
	@Override
	public void delete(Processor processor) throws ServiceException {
		this.repository.delete(processor);
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
	public void importProcessor(Sheet sheet) {
		
		boolean isFirst = Boolean.TRUE;
		for(Iterator<Row> it = sheet.rowIterator(); it.hasNext(); ) {
			try {
				
				Row row = it.next();
				if(isFirst) {
					isFirst = Boolean.FALSE;
					continue;
				}
				
				Socket socket = socketService.findOrCreateByName(
						row.getCell(SOCKET).getStringCellValue().trim());
				
				Manufacturer manufacturer = manufacturerService.findOrCreateByNameAndCategory(
						row.getCell(MANUFACTURER).getStringCellValue().trim(),
						CategoryEnum.PROCESSOR		
				);
				
				Processor processor = new Processor(socket, manufacturer);
				processor.setTitle(row.getCell(NAME).getStringCellValue().trim());
				processor.setPrice(BigDecimal.valueOf(row.getCell(PRICE).getNumericCellValue()));
				processor.setWatts(String.valueOf(row.getCell(WATTS).getNumericCellValue()));
				processor.setCode("0000000000");
				
				processor = this.save(processor);
				
				System.out.println(processor.toString());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ServiceException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
