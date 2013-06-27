package br.com.gp.inventory.domain.service.impl;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.interceptor.Interceptors;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.gp.inventory.domain.service.DriveService;
import br.com.gp.inventory.domain.service.FontService;
import br.com.gp.inventory.domain.service.HardDiskService;
import br.com.gp.inventory.domain.service.MemoryService;
import br.com.gp.inventory.domain.service.MotherboardService;
import br.com.gp.inventory.domain.service.ProcessorService;
import br.com.gp.inventory.domain.service.TowerService;
import br.com.gp.inventory.domain.service.VideoCardService;
import br.com.gp.inventory.domain.service.XLSImporterService;


@Component("xlsImporterService")
@Interceptors(value = {ServiceInteceptor.class})
public class XLSImporterServiceImpl implements XLSImporterService {

	private static List<Integer> listOfSheet;

	private static final int PROCESSADOR = 0;
	private static final int PLACA_MAE = 1;
	private static final int PLACA_VIDEO = 2;
	private static final int MEMORIA = 3;
	private static final int HD = 4;
	private static final int FONTE = 5;
	private static final int GABINETE = 6;
	private static final int DRIVER = 7;
	
	@Autowired
	@Qualifier("processorService")
	private ProcessorService processorService;
	
	@Autowired
	@Qualifier("motherboardService")
	private MotherboardService motherboardService;
	
	@Autowired
	@Qualifier("videoCardService")
	private VideoCardService videoCardService;
	
	@Autowired
	@Qualifier("memoryService")
	private MemoryService memoryService;
	
	@Autowired
	@Qualifier("hardDiskService")
	private HardDiskService hardDiskService;
	
	@Autowired
	@Qualifier("fontService")
	private FontService fontService;
	
	@Autowired
	@Qualifier("towerService")
	private TowerService towerService;
	
	@Autowired
	@Qualifier("driveService")
	private DriveService driveService;

	static {
		listOfSheet = new LinkedList<Integer>();
		listOfSheet.add(PROCESSADOR);
		listOfSheet.add(PLACA_MAE);
		listOfSheet.add(PLACA_VIDEO);
		listOfSheet.add(MEMORIA);
		listOfSheet.add(HD);
		listOfSheet.add(FONTE);
		listOfSheet.add(GABINETE);
		listOfSheet.add(DRIVER);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void importXLS(InputStream in) {

		try {
			
			Workbook workbook = new XSSFWorkbook(OPCPackage.open(in));
			for(Integer key : listOfSheet) {
				Sheet sheet = workbook.getSheetAt(key);
				switch (key) {
				case PROCESSADOR:
					this.processorService.importProcessor(sheet);
					break;
				case PLACA_MAE:
					this.motherboardService.importMotherboard(sheet);
					break;
				case PLACA_VIDEO:
					this.videoCardService.importVideoCard(sheet);
					break;
				case MEMORIA:
					this.memoryService.importMemory(sheet);
					break;
				case HD:
					this.hardDiskService.importHardDisk(sheet);
					break;
				case FONTE:
					this.fontService.importFont(sheet);
					break;
				case GABINETE:
					this.towerService.importTower(sheet);
					break;
				case DRIVER:
					this.driveService.importDriver(sheet);
					break;
				default:
					break;
				}
			}
			
			System.out.println("FIMMMMMMMM");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
