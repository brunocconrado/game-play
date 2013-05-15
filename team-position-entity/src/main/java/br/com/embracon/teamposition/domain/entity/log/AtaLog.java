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

@Entity(name = "TP_ATA_LOG")
@Table(name = "TP_ATA_LOG")
public class AtaLog implements br.com.embracon.j4e.domain.Entity {
	
	private static final long serialVersionUID = 5673676700723466977L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_TP_ATA_LOG")
	@SequenceGenerator(name = "SEQ_TP_ATA_LOG", sequenceName = "SEQ_TP_ATA_LOG", allocationSize = 1)
	@Column(name = "COD_ATA_LOG", scale = 0, precision = 0)
	private Long id;
	
	@Column(name = "NOME", length = 30, nullable = false)
	private String name;
	
	@Column(name = "MES",  scale = 0, precision = 0, nullable = false)
	private Integer month;
	
	@Column(name = "ANO", length = 5, nullable = false)
	private Integer year;
	
	@Column(name = "DESCRICAO", length = 60)
	private String description;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "INICIO", nullable = false)
	private Date start;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "FIM", nullable = false)
	private Date end;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "INICIO_CADASTRO", nullable = false)
	private Date registryStart;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "FIM_CADASTRO", nullable = false)
	private Date registryEnd;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_CRIACAO", nullable = false)
	private Date record;
	
	@Column(name = "ACAO", length = 60)
	private String logAction;
	
	@Transient
	private LogEnum action;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Date getRegistryStart() {
		return registryStart;
	}

	public void setRegistryStart(Date registryStart) {
		this.registryStart = registryStart;
	}

	public Date getRegistryEnd() {
		return registryEnd;
	}

	public void setRegistryEnd(Date registryEnd) {
		this.registryEnd = registryEnd;
	}

	public Date getRecord() {
		return record;
	}

	public void setRecord(Date record) {
		this.record = record;
	}

	public String getLogAction() {
		return logAction;
	}

	public void setLogAction(String logAction) {
		this.logAction = logAction;
	}

	public LogEnum getAction() {
		return action;
	}

	public void setAction(LogEnum action) {
		this.action = action;
		this.logAction = this.action.label();
	}
}
