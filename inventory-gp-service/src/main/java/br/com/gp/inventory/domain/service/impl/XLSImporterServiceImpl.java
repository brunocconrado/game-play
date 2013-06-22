package br.com.gp.inventory.domain.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import br.com.gp.inventory.domain.service.DriveService;
import br.com.gp.inventory.domain.service.FontService;
import br.com.gp.inventory.domain.service.HardDiskService;
import br.com.gp.inventory.domain.service.MemoryService;
import br.com.gp.inventory.domain.service.MotherboardService;
import br.com.gp.inventory.domain.service.ProcessorService;
import br.com.gp.inventory.domain.service.TowerService;
import br.com.gp.inventory.domain.service.VideoCardService;
import br.com.gp.inventory.domain.service.XLSImporterService;

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
	
	private ProcessorService processorService;
	
	private MotherboardService motherboardService;
	
	private VideoCardService videoCardService;
	
	private MemoryService memoryService;
	
	private HardDiskService hardDiskService;
	
	private FontService fontService;
	
	private TowerService towerService;
	
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
	public void importXLS(InputStream in) {

		try {
			HSSFWorkbook workbook = new HSSFWorkbook(in);
			for(Integer key : listOfSheet) {
				HSSFSheet sheet = workbook.getSheetAt(key);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
