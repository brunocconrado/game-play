package br.com.gp.inventory.domain.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.embracon.j4e.reflection.ReflectionUtils;
import br.com.gp.inventory.domain.entity.log.ApprovationLog;

@Entity(name = "TP_APROVACAO")
@Table(name = "TP_APROVACAO")
public class Approvation implements br.com.embracon.j4e.domain.Entity, Cloneable {
	
	
	private static final long serialVersionUID = -3751182802409595668L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_TP_APROVACAO")
	@SequenceGenerator(name = "SEQ_TP_APROVACAO", sequenceName = "SEQ_TP_APROVACAO", allocationSize = 1)
	@Column(name = "COD_APROVACAO", scale = 0, precision = 0)
	private Long id;
	
	@Column(name = "MENSSAGEM", length = 1000)
	private String message;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MATRICULA_APROVADOR", nullable = false)
	private Collaborator approver;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COD_FILIAL", nullable = false)
	private Branch branch;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COD_STATUS", nullable = false)
	private Status status;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_GRACACAO", nullable = false)
	private Date record;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "approvation")
	private Set<TeamSeller> teamSellers = new HashSet<TeamSeller>();
	
	public Approvation() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Collaborator getApprover() {
		return approver;
	}

	public void setApprover(Collaborator approver) {
		this.approver = approver;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Date getRecord() {
		return record;
	}

	public void setRecord(Date record) {
		this.record = record;
	}

	public Set<TeamSeller> getTeamSellers() {
		return teamSellers;
	}

	public void setTeamSellers(Set<TeamSeller> teamSellers) {
		this.teamSellers = teamSellers;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {

		ApprovationLog log = ReflectionUtils.copySameFields(this, ApprovationLog.class);
		log.setId(null);
		log.setBranchId(this.branch.getId());
		log.setRegistryApprover(this.approver.getRegistry());
		log.setStatusId(this.status.getId());
			
		return log;
	}
	
}
