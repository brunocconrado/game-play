package br.com.embracon.teamposition.domain.service.impl;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.embracon.j4e.domain.repository.RepositoryException;
import br.com.embracon.j4e.i18n.Messages;
import br.com.embracon.j4e.io.ByteBuffer;
import br.com.embracon.j4e.io.IOUtils;
import br.com.embracon.j4e.io.vfs.VirtualFile;
import br.com.embracon.j4e.io.vfs.VirtualFileSystemException;
import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.embracon.j4e.util.DateUtils;
import br.com.embracon.j4e.validation.LocalizableReason;
import br.com.embracon.j4e.validation.ValidationException;
import br.com.embracon.j4e.validation.ValidationResult;
import br.com.embracon.teamposition.domain.application.Application;
import br.com.embracon.teamposition.domain.entity.Administrative;
import br.com.embracon.teamposition.domain.entity.Ata;
import br.com.embracon.teamposition.domain.entity.AtaExcecao;
import br.com.embracon.teamposition.domain.entity.Branch;
import br.com.embracon.teamposition.domain.entity.Collaborator;
import br.com.embracon.teamposition.domain.entity.Configuration;
import br.com.embracon.teamposition.domain.entity.Regional;
import br.com.embracon.teamposition.domain.entity.Situation;
import br.com.embracon.teamposition.domain.entity.SoldCode;
import br.com.embracon.teamposition.domain.entity.TeamSeller;
import br.com.embracon.teamposition.domain.entity.log.TeamSellerLog;
import br.com.embracon.teamposition.domain.enumeration.ConfigurationEnum;
import br.com.embracon.teamposition.domain.enumeration.ReportEnum;
import br.com.embracon.teamposition.domain.enumeration.SituationEnum;
import br.com.embracon.teamposition.domain.enumeration.StatusEnum;
import br.com.embracon.teamposition.domain.search.TeamSellerSearch;
import br.com.embracon.teamposition.domain.service.AtaService;
import br.com.embracon.teamposition.domain.service.ConfigurationService;
import br.com.embracon.teamposition.domain.service.ReportService;
import br.com.embracon.teamposition.domain.service.SituationService;
import br.com.embracon.teamposition.domain.service.SoldCodeService;
import br.com.embracon.teamposition.domain.service.TeamSellerLogService;
import br.com.embracon.teamposition.domain.service.TeamSellerService;
import br.com.embracon.teamposition.domain.vo.UserSession;

@Component("reportService")
public class ReportServiceImpl implements ReportService {

	private static final Integer[] YELLOW = new Integer[]{255, 255, 0, 72};
	private static final Integer[] DEEP_SKY = new Integer[]{0, 191, 255, 0x9};
	private static final Integer[] GOLD = new Integer[]{255, 215, 0, 0x10};

	private static final int FIRST_SHEET = 0;

	private static final int SECOND_ROW = 1;

	private HSSFPalette palette;

	@Autowired
	@Qualifier("teamSellerService")
	private TeamSellerService teamSellerService;
	
	@Autowired
	@Qualifier("teamSellerLogService")
	private TeamSellerLogService teamSellerLogService;

	@Autowired
	@Qualifier("ataService")
	private AtaService ataService;

	@Autowired
	@Qualifier("soldCodeService")
	private SoldCodeService soldCodeService;

	@Autowired
	@Qualifier("situationService")
	private SituationService situationService;

	@Autowired
	@Qualifier("configurationService")
	private ConfigurationService configurationService;
	
	@Override
	public List<TeamSeller> search(TeamSellerSearch search) throws ServiceException {

		try {

			return teamSellerService.search(search,
					search.getStatusId() == null ? StatusEnum.PENDENT_MANAGER : 
						StatusEnum.resolve(search.getStatusId()));

		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<TeamSeller> search(TeamSellerSearch search, StatusEnum... status) throws ServiceException {

		try {

			return teamSellerService.search(search, status);

		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void approveReprove(TeamSellerSearch search,
			String message, boolean approved, UserSession user) throws ServiceException {

		try {
			
			ValidationResult result = new ValidationResult();
			if(!user.isManager()) {
				result.addReason(new LocalizableReason("validation.refused.approvation", "Gerente"));
				throw new ValidationException(result);
			}
			
		} catch (ValidationException e) {
			throw e;
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}


	@Override
	public ByteBuffer export(Ata ata, TeamSellerSearch search, boolean compareChanges) throws ServiceException {

		try {

			if(compareChanges) {
				validateRegistryPeriod(ata);
			}

			VirtualFile template = Application.retrive(
					Messages.getMessage("system.templates") + 
					Messages.getMessage("team.positon.report.template"));

			VirtualFile excelTemp = Application.retrive(
					Messages.getMessage("system.temporary.path") + 
					"temp" + DateUtils.getDate().getTime());

			if(!excelTemp.exists()) {
				excelTemp.createFile();
			}

			ByteBuffer buffer = new ByteBuffer();

			IOUtils.copy(template.getContent().getInputStream(), excelTemp.getContent().getOutputStream());

			HSSFWorkbook wb = new HSSFWorkbook(template.getContent().getInputStream());		
			HSSFSheet sheet = wb.getSheetAt(FIRST_SHEET);

			this.palette = wb.getCustomPalette();

			CellStyle style = wb.createCellStyle();
			style.setWrapText(false);

			style.setBorderBottom(CellStyle.BORDER_THIN);
			style.setBorderTop(CellStyle.BORDER_THIN);
			style.setBorderLeft(CellStyle.BORDER_THIN);
			style.setBorderRight(CellStyle.BORDER_THIN);

			Ata lastAta = this.ataService.findByMonthAndYear(
					ata.getMonth() - 1, ata.getYear());

			int rowCount = SECOND_ROW; 

			SoldCode diamant = soldCodeService.findById(SoldCode.DIAMANT);
			Situation transfer = situationService.findBy(SituationEnum.TRANFER);

			List<TeamSeller> reportResult = null;
			if(compareChanges) {
				reportResult = this.search(search, 
						StatusEnum.APPROVED, StatusEnum.REPROVED);
			} else {
				reportResult = this.search(search, search.getStatusId() != null ? 
						StatusEnum.resolve(search.getStatusId()) : StatusEnum.PENDENT_MANAGER);
			}

			boolean hasChanges = false;
			for(TeamSeller teamSeller : reportResult) {
				if(compareChanges && lastAta != null) {
					hasChanges = compareChanges(teamSeller, lastAta);
				} 

				Collaborator seller = teamSeller.getSeller();
				if(hasChanges && seller != null && seller.getSoldCode().equals(diamant)) {
					style.setFillBackgroundColor(createColor(GOLD));
				} else if(seller != null &&  seller.getSoldCode().equals(diamant)) {
					style.setFillBackgroundColor(createColor(DEEP_SKY));
				} else if(hasChanges) {
					style.setFillBackgroundColor(createColor(YELLOW));
				} 

				Row row = sheet.createRow(rowCount);
				row.setRowNum(rowCount);

				//Branch Info
				Branch branch = teamSeller.getBranch();
				createCell(row, style, ReportEnum.BRANCH_CC, branch.getCenterValue());
				createCell(row, style, ReportEnum.BRANCH_CODE, branch.getCode());
				createCell(row, style, ReportEnum.BRANCH_NAME, branch.getName());

				//A Regional Info
				Regional aRegional = branch.getaRegional();
				createCell(row, style, ReportEnum.AREGIONAL_CODE, aRegional.getCode());
				createCell(row, style, ReportEnum.AREGIONAL_NAME, aRegional.getName());

				//Regional Info
				Collaborator regional = branch.getRegional();
				createCell(row, style, ReportEnum.DIR_CODE, regional.getCode());
				createCell(row, style, ReportEnum.REGIONAL_REGISTRY, regional.getRegistry());
				createCell(row, style, ReportEnum.REGIONAL_LOGIN_SCE, regional.getLoginSce());
				createCell(row, style, ReportEnum.REGIONAL_NAME, regional.getName());
				createCell(row, style, ReportEnum.REGIONAL_SITUATION, regional.getSituation().getName());

				//Manager Info
				Collaborator manager = branch.getManager();
				createCell(row, style, ReportEnum.MANAGER_CODE, manager.getCode());
				createCell(row, style, ReportEnum.MANAGER_REGISTRY, manager.getRegistry());
				createCell(row, style, ReportEnum.MANAGER_LOGIN_SCE, manager.getLoginSce());
				createCell(row, style, ReportEnum.MANAGER_NAME, manager.getName());
				createCell(row, style, ReportEnum.MANAGER_SITUATION, manager.getSituation().getName());
				createCell(row, style, ReportEnum.MANAGER_ORIGN_TRANSFER,  
						manager.getSituation().equals(transfer) ? "NOME_ FILIAL" : "");

				//Supervisor Info
				boolean isManagerTeam = teamSeller.isManagerTeam();
				Branch supervisorTransfer = teamSeller.getSupervisorTransfer();
				Collaborator supervisor = teamSeller.getSupervisor();
				createCell(row, style, ReportEnum.SUPERVISOR_CODE, isManagerTeam ? "" : supervisor.getCode());
				createCell(row, style, ReportEnum.SUPERVISOR_REGISTRY, isManagerTeam ? null : supervisor.getRegistry());
				createCell(row, style, ReportEnum.SUPERVISOR_LOGIN_SCE, isManagerTeam ? "" : supervisor.getLoginSce());
				createCell(row, style, ReportEnum.SUPERVISOR_NAME, isManagerTeam ?
						Messages.getMessage("label.manager.team") : supervisor.getName());
				createCell(row, style, ReportEnum.SUPERVISOR_SITUATION, isManagerTeam ? "" : supervisor.getSituation().getName());
				createCell(row, style, ReportEnum.SUPERVISOR_ORIGIN_TRANSFER, isManagerTeam ? "" : ( 
						supervisor.getSituation().equals(transfer) ? supervisorTransfer.getName() : ""));

				//Seller Info
				boolean isSupervisorFired = teamSeller.isSupervisorFired();
				Branch sellerTransfer = teamSeller.getSupervisorTransfer();
				createCell(row, style, ReportEnum.SELLER_CODE, isSupervisorFired ? "" : seller.getCode());
				createCell(row, style, ReportEnum.SELLER_REGISTRY, isSupervisorFired ? null : seller.getRegistry());
				createCell(row, style, ReportEnum.SELLER_LOGIN_SCE, isSupervisorFired ? "" : seller.getLoginSce());
				createCell(row, style, ReportEnum.SELLER_NAME, isSupervisorFired ? "" : seller.getName());
				createCell(row, style, ReportEnum.SELLER_SITUATION, isSupervisorFired ? "" : seller.getSituation().getName());
				createCell(row, style, ReportEnum.SELLER_ORIGN_TRANSFER,  isSupervisorFired ? "" : (
						seller.getSituation().equals(transfer) ? sellerTransfer.getName() : ""));

				//Reseller Info
				createCell(row, style, ReportEnum.RESELLER_CODE, "");
				createCell(row, style, ReportEnum.RESELLER_NAME, "");

				//Administrative Info
				Administrative administrative = branch.getAdministrative() == null 
						? new Administrative() : branch.getAdministrative();
						administrative.administrativesString();
						
				createCell(row, style, ReportEnum.SELLER_CODE, administrative.getCollabortorsString().replace(";", " / "));

				rowCount++;
			}

			wb.write(excelTemp.getContent().getOutputStream());

			IOUtils.copy(excelTemp.getContent().getInputStream(), buffer.getOutputStream());

			return buffer;
		} catch (ValidationException e) {
			throw e;
		} catch (RepositoryException e) {
			throw new ServiceException(e);
		} catch (VirtualFileSystemException e) {
			throw new ServiceException(e);
		} catch (IOException e) {
			throw new ServiceException(e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	private void validateRegistryPeriod(Ata ata) throws ServiceException {

		Configuration config = this.configurationService.findByClazzAndType(
				Ata.class, ConfigurationEnum.DAYS_AFTER_CONCLUDE_REGISTRY);

		Calendar calendar = DateUtils.getCalendar();
		Date today = DateUtils.getDate();
		long time = today.getTime() - ata.getRegistryEnd().getTime();
		calendar.setTimeInMillis(time);
		boolean canExportAllBranhes = calendar.get(Calendar.DAY_OF_YEAR) >= config.getIntegerValue();

		if(canExportAllBranhes && !ata.getExceptions().isEmpty()) {
			for(AtaExcecao excecao : ata.getExceptions()) {
				time = today.getTime() - excecao.getEnd().getTime();
				calendar.setTimeInMillis(time);
				canExportAllBranhes = calendar.get(Calendar.DAY_OF_YEAR) >= config.getIntegerValue();
				if(!canExportAllBranhes) {
					break;
				}
			}
		}

		if(!canExportAllBranhes) {
			ValidationResult result = new ValidationResult();
			result.addReason(new LocalizableReason("validation.registry.not.closed"));
			throw new ValidationException(result);
		}
	}

	private boolean compareChanges(TeamSeller teamSeller, Ata lastAta) throws ServiceException {
		
		TeamSellerLog teamSellerLog = teamSellerLogService
					.findByTeamSellerIdAndAtaId(teamSeller.getId(), lastAta.getId());
		
		Branch branch = teamSeller.getBranch();
		
		Collaborator seller = teamSeller.getSeller() == null 
						? new Collaborator() :  teamSeller.getSeller();
		Collaborator supervisor = teamSeller.getSupervisor() == null 
				? new Collaborator() :  teamSeller.getSupervisor();
		
				
		return teamSellerLog != null && 
				(!teamSellerLog.getBranchId().equals(branch.getId()) ||
					!seller.getId().equals(teamSellerLog.getSellerId()) || 
						(seller.getSituation() != null && !seller.getSituation()
							.getId().equals(teamSellerLog.getSellerSituationId())) ||
								(teamSeller.isSellerTransfer() && !teamSeller.getSellerTransfer()
											.getId().equals(teamSellerLog.getSellerTransferId())) ||
										!teamSeller.getStatus().getId().equals(teamSellerLog.getStatusId()) ||
											!supervisor.getId().equals(teamSellerLog.getSupervisorId()) ||
													(supervisor.getSituation() != null && !supervisor.getSituation()
														.getId().equals(teamSellerLog.getSupervisorId())) ||
																(teamSeller.isSupervisorTransfer() && !teamSeller.getSupervisorTransfer()
																		.getId().equals(teamSellerLog.getSupervisorTransferId())) || 
																			(teamSeller.isManagerTeam() && !teamSellerLog.isManagerTeam()) ||
																				(teamSeller.isSupervisorFired() && !teamSellerLog.isSupervisorFired()));
																				
	}

	private short createColor(Integer[] rgb) {
		
		byte red = rgb[0].byteValue();
		byte green = rgb[1].byteValue();
		byte blue = rgb[2].byteValue();
		
		HSSFColor color = this.palette.findColor(red, green, blue);
		if(color == null) {
			this.palette.setColorAtIndex(rgb[3].shortValue(), red, green, blue);
			color = this.palette.findColor(red, green, blue);
		}
	//	HSSFColor color = this.palette.setaddColor(, , ;
		return color.getIndex();
	}

	private void createCell(Row row, CellStyle style, ReportEnum reportEnum, Integer value) {
		Cell cell = row.createCell(reportEnum.index());	
		cell.setCellStyle(style);
		if(value == null) {
			cell.setCellValue("");	
		} else {
			cell.setCellValue(value);
		}

	}

	private void createCell(Row row, CellStyle style, ReportEnum reportEnum, String value) {
		Cell cell = row.createCell(reportEnum.index());	
		cell.setCellStyle(style);
		cell.setCellValue(value);		
	}


}
