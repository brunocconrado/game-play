package br.com.gp.inventory.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.gp.inventory.domain.enumeration.SituationEnum;

@Entity(name = "TP_COLABORADOR")
@Table(name = "TP_COLABORADOR")
public class Collaborator implements br.com.embracon.j4e.domain.Entity, Comparable<Collaborator> {
	
	
	private static final long serialVersionUID = -2659556496375643696L;

	@Id
	@Column(name = "MATRICULA", scale = 0, precision = 0)
	private Integer registry;
	
	@Column(name = "CODIGO", length = 10)
	private String code;
	
	@Column(name = "NOME", length = 80, nullable = false)
	private String name;
	
	@Column(name = "CPF", length = 11, nullable = false)
	private String cpf;
	
	@Column(name = "EMAIL", length = 60, nullable = false)
	private String email;
	
	@Column(name = "LOGIN_SCE", length = 10)
	private String loginSce;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "ADMISSAO", nullable = false)
	private Date admission;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "INICIO_AFASTAMENTO")
	private Date absenceStart;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "FIM_AFASTAMENTO")
	private Date absenceEnd;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_GRAVACAO", nullable = false)
	private Date record;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_ATUALIZACAO", nullable = false)
	private Date update;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COD_SITUACAO", nullable = false)
	private Situation situation;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COD_ADMINISTRATIVA")
	private Administrative administrative;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COD_CODIGO_VENDA")
	private SoldCode soldCode;
	
	@Transient
	private String cpfFormatted;
	
	public Collaborator() {
		this.situation = new Situation();
	}

	public Long getId() {
		return this.registry == null ? 0L : this.registry.longValue();
	}
	
	public void setId(Long id) {}
	
	public Integer getRegistry() {
		return registry;
	}

	public void setRegistry(Integer registry) {
		this.registry = registry;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf.replace(".", "").replace("-", "");
	}

	public String getLoginSce() {
		return loginSce;
	}

	public void setLoginSce(String loginSce) {
		this.loginSce = loginSce;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getAdmission() {
		return admission;
	}

	public void setAdmission(Date admission) {
		this.admission = admission;
	}

	public Date getAbsenceStart() {
		return absenceStart;
	}

	public void setAbsenceStart(Date absenceStart) {
		this.absenceStart = absenceStart;
	}

	public Date getAbsenceEnd() {
		return absenceEnd;
	}

	public void setAbsenceEnd(Date absenceEnd) {
		this.absenceEnd = absenceEnd;
	}

	public Situation getSituation() {
		return situation;
	}

	public void setSituation(Situation situation) {
		this.situation = situation;
	}

	public Administrative getAdministrative() {
		return administrative;
	}

	public void setAdministrative(Administrative administrative) {
		this.administrative = administrative;
	}

	public SoldCode getSoldCode() {
		return soldCode;
	}

	public void setSoldCode(SoldCode soldCode) {
		this.soldCode = soldCode;
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

	public String getCpfFormatted() {
		formatCpf(cpf);
		return cpfFormatted;
	}

	public void setCpfFormatted(String cpfFormatted) {
		this.cpfFormatted = cpfFormatted;
	}

	public boolean isFired() {
		return this.situation.getId().equals(SituationEnum.FIRED.id());
	}
	
	private void formatCpf(String cpf) {
		if(cpf == null) {
			return;
		}
		
		if(cpf.contains(".") && cpf.contains("-")) {
			this.cpfFormatted = cpf;
		} else {
			while(cpf.length() < 11) {
				cpf = "0" + cpf;
			}
			this.cpfFormatted = cpf.substring(0, 3) + "." + cpf.substring(3, 6) 
					+ "." + cpf.substring(6, 9) + "-"  + cpf.substring(9, 11);
		}
	}
	
	public int hashCode() {
		return this.registry != null ? this.registry.hashCode() : 0;
	}
	
	public boolean equals(Object obj) {
		return obj instanceof Collaborator && this.equals((Collaborator)obj);
	}
	
	public boolean equals(Collaborator other) {
		return this.registry != null && this.registry.equals(other.registry);
	}

	@Override
	public int compareTo(Collaborator other) {
		return this.name.compareTo(other.name);
	}
		
}
