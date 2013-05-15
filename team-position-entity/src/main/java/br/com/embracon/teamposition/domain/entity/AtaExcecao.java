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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "TP_EXCECAO_ATA")
@Table(name = "TP_EXCECAO_ATA")
public class AtaExcecao implements br.com.embracon.j4e.domain.Entity {

	

	private static final long serialVersionUID = -4599722208597392339L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_TP_EXCECAO_ATA")
	@SequenceGenerator(name = "SEQ_TP_EXCECAO_ATA", sequenceName = "SEQ_TP_EXCECAO_ATA", allocationSize = 1)
	@Column(name = "COD_EXCECAO_ATA", scale = 0, precision = 0)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "INICIO", nullable = false)
	private Date start;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "FIM", nullable = false)
	private Date end;
	
	@Column(name = "LOGIN", length = 20, nullable = false)
	private String loginExecutor;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "GRAVACAO", nullable = false)
	private Date record;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COD_FILIAL", nullable = false)
	private Branch branch;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COD_ATA", nullable = false)
	private Ata ata;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getLoginExecutor() {
		return loginExecutor;
	}

	public void setLoginExecutor(String loginExecutor) {
		this.loginExecutor = loginExecutor;
	}

	public Date getRecord() {
		return record;
	}

	public void setRecord(Date record) {
		this.record = record;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	
	public Ata getAta() {
		return ata;
	}

	public void setAta(Ata ata) {
		this.ata = ata;
	}

	public int hashCode() {
		return this.id != null ? this.id.hashCode() : 0;
	}
	
	public boolean equals(Object obj) {
		return obj instanceof AtaExcecao && this.equals((AtaExcecao)obj);
	}
	
	public boolean equals(AtaExcecao other) {
		return this.id == null ? this.branch.equals(other.branch) : (this.id != null && this.id.equals(other.id));
	}
	
}
