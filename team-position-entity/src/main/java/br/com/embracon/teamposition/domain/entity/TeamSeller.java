package br.com.embracon.teamposition.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.embracon.teamposition.domain.entity.log.TeamSellerLog;
import br.com.embracon.teamposition.domain.enumeration.SituationEnum;

@Entity(name = "TP_EQUIPE_VENDEDOR")
@Table(name = "TP_EQUIPE_VENDEDOR")
public class TeamSeller implements br.com.embracon.j4e.domain.Entity {

	
	private static final long serialVersionUID = -507503200255819980L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_EQUIPE_VENDEDOR")
	@SequenceGenerator(name = "SEQ_EQUIPE_VENDEDOR", sequenceName = "SEQ_EQUIPE_VENDEDOR")
	@Column(name = "COD_EQUIPE_VENDEDOR", scale = 0, precision = 0)
	private Long id;

	@Column(name = "DEMISSAO_SUPERVISOR")
	private boolean supervisorFired;
	
	@Column(name = "EQUIPE_GERENCIA")
	private boolean managerTeam;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_GRAVACAO", nullable = false)
	private Date record; 
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_ATUALIZACAO", nullable = false)
	private Date update;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COD_STATUS", nullable = false)
	private Status status;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COD_FILIAL", nullable = false)
	private Branch branch;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COD_TRANSF_VEND_FILIAL")
	private Branch sellerTransfer;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COD_TRANSF_SUPER_FILIAL")
	private Branch supervisorTransfer;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MATRICULA_VENDEDOR")
	private Collaborator seller;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MATRICULA_SUPERVISOR")
	private Collaborator supervisor;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COD_APROVACAO")
	private Approvation approvation;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COD_ATA", nullable = false)
	private Ata ata;
	
	public TeamSeller() {
		this.branch = new Branch();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isSupervisorFired() {
		return supervisorFired;
	}

	public void setSupervisorFired(boolean supervisorFired) {
		this.supervisorFired = supervisorFired;
	}

	public boolean isManagerTeam() {
		return managerTeam;
	}

	public void setManagerTeam(boolean managerTeam) {
		this.managerTeam = managerTeam;
	}

	public Date getRecord() {
		return record;
	}

	public void setRecord(Date record) {
		this.record = record;
	}

	public Date getUpdate() {
		return update;
	}

	public void setUpdate(Date update) {
		this.update = update;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Branch getSellerTransfer() {
		return sellerTransfer;
	}

	public void setSellerTransfer(Branch sellerTransfer) {
		this.sellerTransfer = sellerTransfer;
	}

	public Branch getSupervisorTransfer() {
		return supervisorTransfer;
	}

	public void setSupervisorTransfer(Branch supervisorTransfer) {
		this.supervisorTransfer = supervisorTransfer;
	}

	public Collaborator getSeller() {
		return seller;
	}

	public void setSeller(Collaborator seller) {
		this.seller = seller;
	}

	public Collaborator getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Collaborator supervisor) {
		this.supervisor = supervisor;
	}

	public Approvation getApprovation() {
		return approvation;
	}

	public void setApprovation(Approvation approvation) {
		this.approvation = approvation;
	}

	public Ata getAta() {
		return ata;
	}

	public void setAta(Ata ata) {
		this.ata = ata;
	}
	
	public boolean isSellerTransfer() {
		return this.sellerTransfer != null;
	}
	
	public boolean isSupervisorTransfer() {
		return this.supervisorTransfer != null;
	}
	
	public boolean isAbsenceSupervisor() {
		return this.supervisor.getSituation()
					.getId().equals(SituationEnum.ABSENCE.id());
	}

	public boolean isAbsenceSeller() {
		return this.seller.getSituation()
					.getId().equals(SituationEnum.ABSENCE.id());
	}
	public int hashCode() {
		return this.id != null ? this.id.hashCode() : 0;
	}
	
	public boolean equals(Object obj) {
		return obj instanceof TeamSeller && this.equals((TeamSeller)obj);
	}
	
	public boolean equals(TeamSeller other) {
		return this.id != null && this.id.equals(other.id);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {

		TeamSellerLog log = new TeamSellerLog();
		log.setTeamSellerId(this.id);
		log.setApprovationId(this.approvation == null ? null : this.approvation.getId());
		log.setAtaId(this.ata.getId());
		log.setBranchId(this.branch.getId());
		log.setSellerId(this.seller == null ? null : this.seller.getRegistry());
		log.setSellerSituationId(this.seller == null ? null : this.seller.getSituation().getId());
		log.setSupervisorId(this.supervisor == null ? null : this.supervisor.getRegistry());
		log.setSupervisorSituationId(this.supervisor == null ? null : this.supervisor.getSituation().getId());
		log.setStatusId(this.status.getId());
		log.setSellerTransferId(this.sellerTransfer == null ? null : this.sellerTransfer.getId());
		log.setSupervisorTransferId(this.supervisorTransfer == null ? null : this.supervisorTransfer.getId());
		log.setManagerTeam(this.managerTeam);
		log.setSupervisorFired(this.supervisorFired);
		
		return log;
	
	}
}