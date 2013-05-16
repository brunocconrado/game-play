package br.com.embracon.teamposition.domain.entity.log;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "TP_APROVACAO_LOG")
@Table(name = "TP_APROVACAO_LOG")
public class ApprovationLog implements br.com.embracon.j4e.domain.Entity {
	

	private static final long serialVersionUID = -9190859908873515002L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_TP_APROVACAO_LOG")
	@SequenceGenerator(name = "SEQ_TP_APROVACAO_LOG", sequenceName = "SEQ_TP_APROVACAO_LOG", allocationSize = 1)
	@Column(name = "COD_APROVACAO_LOG", scale = 0, precision = 0)
	private Long id;
	
	@Column(name = "MENSSAGEM", length = 1000)
	private String message;
	
	@Column(name = "COD_STATUS", scale = 0, precision = 0)
	private Long statusId;
	
	@Column(name = "COD_FILIAL", scale = 0, precision = 0)
	private Long branchId;
	
	@Column(name = "MATRICULA_APROVADOR", nullable = false)
	private Integer registryApprover;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_GRACACAO", nullable = false)
	private Date record;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public Integer getRegistryApprover() {
		return registryApprover;
	}

	public void setRegistryApprover(Integer registryApprover) {
		this.registryApprover = registryApprover;
	}

	public Date getRecord() {
		return record;
	}

	public void setRecord(Date record) {
		this.record = record;
	}
	
	public int hashCode() {
		return this.id != null ? this.id.hashCode() : 0;
	}
	
	public boolean equals(Object obj) {
		return obj instanceof ApprovationLog && this.equals((ApprovationLog)obj);
	}
	
	public boolean equals(ApprovationLog other) {
		return this.id != null && this.id.equals(other.id);
	}

}
