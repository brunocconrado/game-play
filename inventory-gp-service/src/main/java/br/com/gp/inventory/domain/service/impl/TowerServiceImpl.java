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
import br.com.gp.inventory.domain.entity.Manufacturer;
import br.com.gp.inventory.domain.entity.Tower;
import br.com.gp.inventory.domain.enumeration.CategoryEnum;
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
	public void delete(Tower tower) throws ServiceException {
		this.repository.delete(tower);
	}

	@Override
	public Tower findById(Long id) throws ServiceException {
		return this.repository.findByIdentity(id);
	}

	@Override
	public void importTower(Sheet sheet) {
		
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
						CategoryEnum.TOWER
				);
				
				Tower tower = new Tower(manufacturer);
				
				tower.setName(row.getCell(NAME).getStringCellValue().trim());
				tower.setPrice(BigDecimal.valueOf(row.getCell(PRICE).getNumericCellValue()));
				tower.setWatts(String.valueOf(row.getCell(WATTS).getNumericCellValue()));
				tower.setCode("0000000000");
				
				tower = this.save(tower);
				
				System.out.println(tower.toString());
			} catch (ServiceException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
