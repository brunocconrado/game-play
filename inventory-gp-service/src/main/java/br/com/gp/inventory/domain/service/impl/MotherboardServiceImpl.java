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
import br.com.gp.inventory.domain.entity.Socket;
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
		return this.repository.save(motherboard);	
	}

	@Override
	public Motherboard findById(Long id) throws ServiceException {
		return this.repository.findByIdentity(id);
	}

	@Override
	public void importMotherboard(HSSFSheet sheet) {
		for(Iterator<Row> it = sheet.rowIterator(); it.hasNext(); ) {
			try {
				
				Row row = it.next();
				Socket socket = socketService.findOrCreateByName(
						row.getCell(SOCKET).getStringCellValue().trim());

				Manufacturer manufacturer = manufacturerService.findOrCreateByName(
						row.getCell(MANUFACTURER).getStringCellValue().trim());
				
				Motherboard motherboard = new Motherboard(socket, manufacturer);
				motherboard.setTitle(row.getCell(NAME).getStringCellValue().trim());
				motherboard.setPriceString(row.getCell(PRICE).getStringCellValue().trim());
				motherboard.setWatts(row.getCell(WATTS).getStringCellValue().trim());
				motherboard.setCode("0000000000");
				
				motherboard = this.save(motherboard);
				motherboard.setCode(StringUtils.formatString(motherboard.getId(), 10, "PM"));
				motherboard = this.save(motherboard);
				
				System.out.println(motherboard.toString());
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
	}

}
