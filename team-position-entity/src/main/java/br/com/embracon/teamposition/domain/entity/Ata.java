package br.com.embracon.teamposition.domain.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.embracon.j4e.reflection.ReflectionUtils;
import br.com.embracon.teamposition.domain.entity.log.AtaLog;

@Entity(name = "TP_ATA")
@Table(name = "TP_ATA")
public class Ata implements br.com.embracon.j4e.domain.Entity, Cloneable, Comparable<Ata> {
	
	
	private static final long serialVersionUID = 136394275480552500L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_TP_ATA")
	@SequenceGenerator(name = "SEQ_TP_ATA", sequenceName = "SEQ_TP_ATA", allocationSize = 1)
	@Column(name = "COD_ATA", scale = 0, precision = 0)
	private Long id;
	
	@Column(name = "NOME", length = 30, nullable = false)
	private String name;
	
	@Column(name = "MES", scale = 0, precision = 0, nullable = false)
	private Integer month;
	
	@Column(name = "ANO", scale = 0, precision = 0, nullable = false)
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_CRIACAO", nullable = false)
	private Date record;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ata")
	private Set<AtaExcecao> exceptions = new HashSet<AtaExcecao>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ata")
	private Set<TeamSeller> teamSellers = new HashSet<TeamSeller>();
	
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

	public Set<AtaExcecao> getExceptions() {
		return exceptions;
	}

	public void setExceptions(Set<AtaExcecao> exceptions) {
		this.exceptions = exceptions;
	}
	
	public Set<TeamSeller> getTeamSellers() {
		return teamSellers;
	}

	public void setTeamSellers(Set<TeamSeller> teamSellers) {
		this.teamSellers = teamSellers;
	}

	public int hashCode() {
		return this.id != null ? this.id.hashCode() : 0;
	}
	
	public boolean equals(Object obj) {
		return obj instanceof Ata && this.equals((Ata)obj);
	}
	
	public boolean equals(Ata other) {
		return this.id != null && this.id.equals(other.id);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		AtaLog log = ReflectionUtils.copySameFields(this, AtaLog.class);
		log.setId(null);
		
		return log;
	}

	@Override
	public int compareTo(Ata other) {
		return this.name.compareTo(other.name);
	}
	
}
