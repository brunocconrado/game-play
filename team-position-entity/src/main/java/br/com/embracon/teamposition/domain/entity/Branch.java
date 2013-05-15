package br.com.embracon.teamposition.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.embracon.j4e.reflection.ReflectionUtils;
import br.com.embracon.teamposition.domain.entity.log.BranchLog;

@Entity(name = "TP_FILIAL")
@Table(name = "TP_FILIAL")
public class Branch implements br.com.embracon.j4e.domain.Entity, Comparable<Branch>, Cloneable {
	
	

	private static final long serialVersionUID = 5109335522771788165L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_TP_FILIAL")
	@SequenceGenerator(name = "SEQ_TP_FILIAL", sequenceName = "SEQ_TP_FILIAL")
	@Column(name = "COD_FILIAL", scale = 0, precision = 0)
	private Long id;
	
	@Column(name = "CODIGO", length = 10, nullable = false)
	private String code;
	
	@Column(name = "NOME", length = 80, nullable = false)
	private String name;
	
	@Column(name = "RAZAO_SOCIAL", length = 100, nullable = false)
	private String socialReason;
	
	@Column(name = "CENTRO_CUSTO", length = 10, nullable = false)
	private String centerValue;
	
	@Column(name = "SEM_GERENCIA", nullable = true)
	private boolean withoutmanager;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MATRICULA_REGIONAL", nullable = false)
	private Collaborator regional;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MATRICULA_GERENTE", nullable = false)
	private Collaborator manager;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MATRICULA_SUP_CAMPO")
	private Collaborator fieldSupervisor;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COD_ADMINISTRATIVA")
	private Administrative administrative;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COD_REGIONAL", nullable = false)
	private Regional aRegional;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COD_STATUS", nullable = false)
	private Status status;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_GRAVACAO", nullable = false)
	private Date record;
	
	public Branch(){}
	
	public Branch(String code, String name, String centervalue) {
		this.code = code;
		this.name = name;
		this.centerValue = centervalue;
	}

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

	public Collaborator getRegional() {
		return regional;
	}

	public void setRegional(Collaborator regional) {
		this.regional = regional;
	}

	public Collaborator getManager() {
		return manager;
	}

	public void setManager(Collaborator manager) {
		this.manager = manager;
	}

	public Collaborator getFieldSupervisor() {
		return fieldSupervisor;
	}

	public void setFieldSupervisor(Collaborator fieldSupervisor) {
		this.fieldSupervisor = fieldSupervisor;
	}

	public Administrative getAdministrative() {
		return administrative;
	}

	public void setAdministrative(Administrative administrative) {
		this.administrative = administrative;
	}

	public Regional getaRegional() {
		return aRegional;
	}

	public void setaRegional(Regional aRegional) {
		this.aRegional = aRegional;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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
		return obj instanceof Branch && this.equals((Branch)obj);
	}
	
	public boolean equals(Branch other) {
		return this.id != null && this.id.equals(other.id);
	}

	@Override
	public int compareTo(Branch other) {
		return this.name.compareTo(other.name);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		BranchLog clone = ReflectionUtils.copySameFields(this, BranchLog.class);
		clone.setId(null);
		clone.setaRegionalId(this.aRegional.getId());
		clone.setRegionalId(this.regional.getId());
		clone.setManagerId(this.manager.getId());
		clone.setStatusId(this.status.getId());
		clone.setWithoutmanager(this.withoutmanager);
		clone.setFieldSupervisorId(this.fieldSupervisor != null ? this.fieldSupervisor.getId() : null);
		
		return clone;
	}

}
