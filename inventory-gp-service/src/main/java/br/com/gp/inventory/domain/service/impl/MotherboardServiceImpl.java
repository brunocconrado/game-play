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
import br.com.gp.inventory.domain.entity.Motherboard;
import br.com.gp.inventory.domain.entity.Socket;
import br.com.gp.inventory.domain.enumeration.CategoryEnum;
import br.com.gp.inventory.domain.repository.MotherboardRepository;
import br.com.gp.inventory.domain.service.ManufacturerService;
import br.com.gp.inventory.domain.service.MotherboardService;
import br.com.gp.inventory.domain.service.SocketService;
import br.com.gp.inventory.domain.util.StringUtils;

@Component("motherboardService")
@Interceptors(value = {ServiceInteceptor.class})
public class MotherboardServiceImpl implements MotherboardService {

	private static final int SOCKET = 0;
	private static final int MANUFACTURER = 1;
	private static final int NAME = 2;
	private static final int PRICE = 3;
	//private static final int PARCEL = 4;
	private static final int WATTS = 5;

	@Autowired
	@Qualifier(value = "motherboardRepository")
	private MotherboardRepository repository;

	@Autowired
	@Qualifier(value = "socketService")
	private SocketService socketService;

	@Autowired
	@Qualifier(value = "manufacturerService")
	private ManufacturerService manufacturerService;

	@Override
	public List<Motherboard> findAll() throws ServiceException {
		return (List<Motherboard>) repository.findAll();
	}

	@Override
	public Motherboard save(Motherboard motherboard) throws ServiceException {
		motherboard = this.repository.save(motherboard);
		motherboard.setCode(StringUtils.formatString(motherboard.getId(), 10, "PM"));
		return this.repository.save(motherboard);	
	}

	@Override
	public Motherboard findById(Long id) throws ServiceException {
		return this.repository.findByIdentity(id);
	}
<<<<<<< HEAD
	
	@Override
	public void delete(Motherboard motherboard) throws ServiceException {
		this.repository.delete(motherboard);
	}
=======

	@Override
	public void importMotherboard(Sheet sheet) {
		
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
						CategoryEnum.MOTHERBOARD
				);
				
				Motherboard motherboard = new Motherboard(socket, manufacturer);
				motherboard.setTitle(row.getCell(NAME).getStringCellValue().trim());
				motherboard.setPrice(BigDecimal.valueOf(row.getCell(PRICE).getNumericCellValue()));
				motherboard.setWatts(String.valueOf(row.getCell(WATTS).getNumericCellValue()));
				motherboard.setCode("0000000000");
				
				motherboard = this.save(motherboard);
				
				System.out.println(motherboard.toString());
			} catch (ServiceException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

>>>>>>> ccdd4fa67d62621782d619c6692d92cf3cbe985b
}
