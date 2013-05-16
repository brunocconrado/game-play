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
import javax.persistence.Transient;

import br.com.embracon.teamposition.domain.enumeration.LogEnum;

@Entity(name = "TP_FILIAL_LOG")
@Table(name = "TP_FILIAL_LOG")
public class BranchLog implements br.com.embracon.j4e.domain.Entity {

	
	private static final long serialVersionUID = 8937588526030294685L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_TP_FILIAL_LOG")
	@SequenceGenerator(name = "SEQ_TP_FILIAL_LOG", sequenceName = "SEQ_TP_FILIAL_LOG", allocationSize = 1)
	@Column(name = "COD_FILIAL_LOG", scale = 0, precision = 0)
	private Long id;

	@Column(name = "CODIGO", length = 10, nullable = false)
	private String code;
	
	@Column(name = "NOME", length = 80, nullable = false)
	private String name;
	
	@Column(name = "RAZAO_SOCIAL", length = 100, nullable = false)
	private String socialReason;
	
	@Column(name = "CENTRO_CUSTO", length = 10, nullable = false)
	private String centerValue;
	
	@Column(name = "SEM_GERENCIA", nullable = false)
	private boolean withoutmanager;
	
	@Column(name = "COD_STATUS", length = 10, nullable = false)
	private Long statusId;
	
	@Column(name = "MATRICULA_REGIONAL", length = 10, nullable = false)
	private Long regionalId;
	
	@Column(name = "MATRICULA_GERENTE", length = 10, nullable = false)
	private Long managerId;
	
	@Column(name = "MATRICULA_SUP_CAMPO", scale = 0, precision = 0)
	private Long fieldSupervisorId;
	
	@Column(name = "COD_REGIONAL", length = 10, nullable = false)
	private Long aRegionalId;
	
	@Column(name = "MATRICULA_EDITOR", length = 10)
	private Integer editorRegistry;
	
	@Column(name = "ACAO", length = 60)
	private String logAction;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_GRAVACAO", length = 10, nullable = false)
	private Date record;

	@Transient
	private LogEnum action;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSocialReason() {
		return socialReason;
	}

	public void setSocialReason(String socialReason) {
		this.socialReason = socialReason;
	}

	public String getCenterValue() {
		return centerValue;
	}

	public void setCenterValue(String centerValue) {
		this.centerValue = centerValue;
	}

	public boolean isWithoutmanager() {
		return withoutmanager;
	}

	public void setWithoutmanager(boolean withoutmanager) {
		this.withoutmanager = withoutmanager;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public Long getRegionalId() {
		return regionalId;
	}

	public void setRegionalId(Long regionalId) {
		this.regionalId = regionalId;
	}

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	public Long getFieldSupervisorId() {
		return fieldSupervisorId;
	}

	public void setFieldSupervisorId(Long fieldSupervisorId) {
		this.fieldSupervisorId = fieldSupervisorId;
	}

	public Long getaRegionalId() {
		return aRegionalId;
	}

	public void setaRegionalId(Long aRegionalId) {
		this.aRegionalId = aRegionalId;
	}

	public Integer getEditorRegistry() {
		return editorRegistry;
	}

	public void setEditorRegistry(Integer editorRegistry) {
		this.editorRegistry = editorRegistry;
	}

	public String getLogAction() {
		return logAction;
	}

	public void setLogAction(String logAction) {
		this.logAction = logAction;
	}

	public Date getRecord() {
		return record;
	}

	public void setRecord(Date record) {
		this.record = record;
	}

	public LogEnum getAction() {
		return action;
	}

	public void setAction(LogEnum action) {
		this.action = action;
		this.logAction = this.action.label();
	}
	
}
