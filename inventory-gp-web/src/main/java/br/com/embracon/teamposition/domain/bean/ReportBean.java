package br.com.embracon.teamposition.domain.bean;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.embracon.j4e.i18n.Messages;
import br.com.embracon.j4e.io.ByteBuffer;
import br.com.embracon.j4e.io.IOUtils;
import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.embracon.j4e.util.DateUtils;
import br.com.embracon.j4e.validation.ValidationException;
import br.com.embracon.teamposition.domain.entity.Approvation;
import br.com.embracon.teamposition.domain.entity.Ata;
import br.com.embracon.teamposition.domain.entity.Branch;
import br.com.embracon.teamposition.domain.entity.Status;
import br.com.embracon.teamposition.domain.entity.TeamSeller;
import br.com.embracon.teamposition.domain.enumeration.StatusEnum;
import br.com.embracon.teamposition.domain.search.TeamSellerSearch;
import br.com.embracon.teamposition.domain.service.AtaService;
import br.com.embracon.teamposition.domain.service.BranchService;
import br.com.embracon.teamposition.domain.service.ReportService;
import br.com.embracon.teamposition.domain.service.StatusService;
import br.com.embracon.teamposition.domain.vo.UserSession;
import br.com.embracon.teamposition.utils.TeamPositionProperties;


@Controller("reportBean")
@Scope("session")
public class ReportBean extends DefaultBean {
	
	private static final String REPORT = Messages.getMessage("label.report");
	private static final String APPROVE_REPPROVE = Messages.getMessage("label.approve.reprove");
	
	@Autowired
	@Qualifier("reportService")
	private ReportService service;
	
	@Autowired
	@Qualifier("branchService")
	private BranchService branchService;
	
	@Autowired
	@Qualifier("ataService")
	private AtaService ataService;
	
	@Autowired
	@Qualifier("statusService")
	private StatusService statusService;
	
	private Approvation approvation;
	
	private List<Branch> branches;
	
	private List<Ata> atas;
	
	private List<TeamSeller> reportResult;
	
	private List<Status> status;
	
	private TeamSellerSearch search;
	
	private UserSession user;
	
	private boolean approved = true;
	
	private boolean canBeApproved;
	
	private boolean showButtom;
	
	public Approvation getApprovation() {
		return approvation;
	}

	public void setApprovation(Approvation approvation) {
		this.approvation = approvation;
	}

	public List<Branch> getBranches() {
		return branches;
	}

	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}

	public List<Ata> getAtas() {
		return atas;
	}

	public void setAtas(List<Ata> atas) {
		this.atas = atas;
	}
	
	public List<TeamSeller> getReportResult() {
		return reportResult;
	}

	public void setReportResult(List<TeamSeller> reportResult) {
		this.reportResult = reportResult;
	}

	public TeamSellerSearch getSearch() {
		return search;
	}

	public void setSearch(TeamSellerSearch search) {
		this.search = search;
	}

	public List<Status> getStatus() {
		return status;
	}

	public void setStatus(List<Status> status) {
		this.status = status;
	}

	public boolean isCanBeApproved() {
		return canBeApproved;
	}

	public void setCanBeApproved(boolean canBeApproved) {
		this.canBeApproved = canBeApproved;
	}
	
	public boolean isShowButtom() {
		return showButtom;
	}

	public void setShowButtom(boolean showButtom) {
		this.showButtom = showButtom;
	}

	public UserSession getUser() {
		return user;
	}

	public void setUser(UserSession user) {
		this.user = user;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public ReportBean() {
		super("reportBean");
		this.approvation = new Approvation();
		this.search = new TeamSellerSearch();
		this.reportResult = new ArrayList<TeamSeller>();
	}
	
	@PostConstruct
	public void init() {
		
		try {
			
			this.user = (UserSession) getFromSession(
								TeamPositionProperties.USER_LOGGED); 
			
			if(this.user.isManager()) {
				Ata ata = ataService.findByRegistryDate(DateUtils.getOnlyDate());
				if(ata == null) {
					this.canBeApproved = Boolean.FALSE;
					this.showButtom = Boolean.FALSE;
					warningMessage("validation.expired.registry.date", APPROVE_REPPROVE);
					return;
				}
				this.atas = new ArrayList<Ata>();
				this.atas.add(ata);
				this.search.setAtaId(ata.getId());
				
				Branch branch = branchService.findByCollaboratorRegistry(this.user.getRegistry());
				this.branches = new ArrayList<Branch>();
				if(branch != null) {
					this.search.setBranchId(branch.getId());
					this.branches.add(branch);
				}
				
				Status status = this.statusService.findBy(StatusEnum.PENDENT_MANAGER);
				this.status = new ArrayList<Status>();
				this.status.add(status);
				this.search.setStatusId(status.getId());
				
				this.canBeApproved = Boolean.TRUE;
			} else if(user.isCommercial()) {
				this.atas = this.ataService.findAll();
				this.status = this.statusService.findAllActives();
				this.branches = this.branchService.findAllActive();
				this.showButtom = Boolean.TRUE;
			}
			
		} catch (ServiceException e) {
			errorMessage("error.message.init", REPORT);
		} catch (Exception e) {	
			fatalMessage("fatal.error", e);
		}
	}

	public void searchReport() {
		
		try {
			this.reportResult = this.service.search(this.search);
		}  catch (ServiceException e) {
			errorMessage("error.search", REPORT);
		} catch (Exception e) {	
			fatalMessage("fatal.error", e);
		}	
	}
	
	public void exportToExcel() {
		
		try {
			
			Ata ata = this.ataService.findById(this.search.getAtaId(), true);
			
			String documentName = "PE - " + ata.getName().toLowerCase() 
					+ " " + ata.getYear() + (user.isCommercial() ? " - COMISSOES" : "") + ".xls";
			
			ByteBuffer excel = this.service.export(ata, this.search, this.user.isCommercial()
					&& (this.search.getBranchId() == null &&  this.search.getStatusId() == null));
			
			HttpServletResponse response = getResponse();
			response.setContentType("application/excel");
			response.setHeader("Content-disposition", "attachment;filename=" + documentName);

			IOUtils.copy(excel.getInputStream(), response.getOutputStream());
			
			responseComplete();
		}  catch (ValidationException e) {
			warningMessage(e.getResult().getReasons());
		}  catch (ServiceException e) {
			errorMessage("error.search", REPORT);
		} catch (Exception e) {	
			fatalMessage("fatal.error", e);
		}	
	}
	
	public void finishApprovation() {
		try {
			
			this.service.approveReprove(this.search, 
					this.approvation.getMessage(), approved, this.user);
			
			successMessage("save.success", Messages.getMessage("label.approve.reprove"));
			
			this.reportResult.clear();
			
		}  catch (ValidationException e) {
			warningMessage(e.getResult().getReasons());
		}  catch (ServiceException e) {
			errorMessage("error.search", REPORT);
		} catch (Exception e) {	
			fatalMessage("fatal.error", e);
		}	
	}
	
}
