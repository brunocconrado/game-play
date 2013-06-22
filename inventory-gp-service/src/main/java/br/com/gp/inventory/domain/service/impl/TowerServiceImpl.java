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
import br.com.gp.inventory.domain.entity.Tower;
import br.com.gp.inventory.domain.repository.TowerRepository;
import br.com.gp.inventory.domain.service.ManufacturerService;
import br.com.gp.inventory.domain.service.TowerService;
import br.com.gp.inventory.domain.util.StringUtils;

@Component("towerService")
@Interceptors(value = {ServiceInteceptor.class})
public class TowerServiceImpl implements TowerService {
	
	private static final int MANUFACTURER = 0;
	private static final int NAME = 1;
	private static final int PRICE = 2;
	//private static final int PARCEL = 3;
	private static final int WATTS = 4;

	@Autowired
	@Qualifier(value = "towerRepository")
	private TowerRepository repository;
	
	@Autowired
	@Qualifier(value = "manufacturerService")
	private ManufacturerService manufacturerService;	
	
	@Override
	public Tower save(Tower tower) throws ServiceException {
		tower = this.repository.save(tower);
		tower.setCode(StringUtils.formatString(tower.getId(), 10, "G"));
		return this.repository.save(tower);
	}

	@Override
	public List<Tower> findAll() throws ServiceException {
		return (List<Tower>) this.repository.findAll();
	}

	@Override
	public Tower findById(Long id) throws ServiceException {
		return this.repository.findByIdentity(id);
	}

	@Override
	public void importTower(HSSFSheet sheet) {
		for(Iterator<Row> it = sheet.rowIterator(); it.hasNext(); ) {
			try {
				
				Row row = it.next();

				Manufacturer manufacturer = manufacturerService.findOrCreateByName(
						row.getCell(MANUFACTURER).getStringCellValue().trim());
				
				Tower tower = new Tower(manufacturer);
				
				tower.setName(row.getCell(NAME).getStringCellValue().trim());
				tower.setPriceString(row.getCell(PRICE).getStringCellValue().trim());
				tower.setWatts(row.getCell(WATTS).getStringCellValue().trim());
				tower.setCode("0000000000");
				
				tower = this.save(tower);
				
				System.out.println(tower.toString());
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
	}
}
