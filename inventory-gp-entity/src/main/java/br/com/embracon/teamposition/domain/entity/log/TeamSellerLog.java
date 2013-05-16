package br.com.embracon.teamposition.domain.entity.log;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "TP_EQUIPE_VENDEDOR_LOG")
@Table(name = "TP_EQUIPE_VENDEDOR_LOG")
public class TeamSellerLog implements br.com.embracon.j4e.domain.Entity {


	private static final long serialVersionUID = -5356095137528681587L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "TP_EQUIPE_VENDEDOR_LOG")
	@SequenceGenerator(name = "TP_EQUIPE_VENDEDOR_LOG", sequenceName = "TP_EQUIPE_VENDEDOR_LOG")
	@Column(name = "COD_EQUIPE_VENDEDOR_LOG", scale = 0, precision = 0)
	private Long id;

	@Column(name = "COD_EQUIPE_VENDEDOR", scale = 0, precision = 0)
	private Long teamSellerId;

	@Column(name = "COD_FILIAL", scale = 0, precision = 0)
	private Long branchId;

	@Column(name = "MATRICULA_VENDEDOR", scale = 0, precision = 0)
	private Integer sellerId;

	@Column(name = "MATRICULA_SUPERVISOR", scale = 0, precision = 0)
	private Integer supervisorId;

	@Column(name = "COD_APROVACAO", scale = 0, precision = 0)
	private Long approvationId;

	@Column(name = "COD_SITUACAO_VENDEDOR", scale = 0, precision = 0)
	private Long sellerSituationId;

	@Column(name = "COD_SITUACAO_SUPERVISOR", scale = 0, precision = 0)
	private Long supervisorSituationId;

	@Column(name = "COD_ATA", scale = 0, precision = 0)
	private Long ataId;

	@Column(name = "COD_STATUS", scale = 0, precision = 0)
	private Long statusId;

	@Column(name = "DATA_GRAVACAO", scale = 0, precision = 0)
	private Date record;

	@Column(name = "COD_FIL_TRANSF_VENDEDOR", scale = 0, precision = 0)
	private Long sellerTransferId;

	@Column(name = "COD_FIL_TRANSF_SUPERVISOR", scale = 0, precision = 0)
	private Long supervisorTransferId;

	@Column(name = "EQUIPE_GERENCIA", scale = 0, precision = 0)
	private boolean managerTeam;

	@Column(name = "DEMISSAO_SUPERVISOR", scale = 0, precision = 0)
	private boolean supervisorFired;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTeamSellerId() {
		return teamSellerId;
	}

	public void setTeamSellerId(Long teamSellerId) {
		this.teamSellerId = teamSellerId;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public Integer getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(Integer supervisorId) {
		this.supervisorId = supervisorId;
	}

	public Long getApprovationId() {
		return approvationId;
	}

	public void setApprovationId(Long approvationId) {
		this.approvationId = approvationId;
	}

	public Long getSellerSituationId() {
		return sellerSituationId;
	}

	public void setSellerSituationId(Long sellerSituationId) {
		this.sellerSituationId = sellerSituationId;
	}

	public Long getSupervisorSituationId() {
		return supervisorSituationId;
	}

	public void setSupervisorSituationId(Long supervisorSituationId) {
		this.supervisorSituationId = supervisorSituationId;
	}

	public Long getAtaId() {
		return ataId;
	}

	public void setAtaId(Long ataId) {
		this.ataId = ataId;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public Date getRecord() {
		return record;
	}

	public void setRecord(Date record) {
		this.record = record;
	}

	public Long getSellerTransferId() {
		return sellerTransferId;
	}

	public void setSellerTransferId(Long sellerTransferId) {
		this.sellerTransferId = sellerTransferId;
	}

	public Long getSupervisorTransferId() {
		return supervisorTransferId;
	}

	public void setSupervisorTransferId(Long supervisorTransferId) {
		this.supervisorTransferId = supervisorTransferId;
	}

	public boolean isManagerTeam() {
		return managerTeam;
	}

	public void setManagerTeam(boolean managerTeam) {
		this.managerTeam = managerTeam;
	}

	public boolean isSupervisorFired() {
		return supervisorFired;
	}

	public void setSupervisorFired(boolean supervisorFired) {
		this.supervisorFired = supervisorFired;
	}

	
}

