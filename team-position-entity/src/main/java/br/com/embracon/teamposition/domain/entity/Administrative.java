package br.com.embracon.teamposition.domain.entity;

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
import javax.persistence.Transient;

@Entity(name = "TP_ADMINISTRATIVA")
@Table(name = "TP_ADMINISTRATIVA")
public class Administrative implements br.com.embracon.j4e.domain.Entity {
	

	private static final long serialVersionUID = -6832995014406484961L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_TP_ADMINISTRATIVA")
	@SequenceGenerator(name = "SEQ_TP_ADMINISTRATIVA", sequenceName = "SEQ_TP_ADMINISTRATIVA", allocationSize = 1)
	@Column(name = "COD_ADMINISTRATIVA", scale = 0, precision = 0)
	private Long id;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COD_FILIAL", nullable = false)
	private Branch branch;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "administrative")
	private Set<Collaborator> colaborators = new HashSet<Collaborator>();
	
	@Transient
	private String collabortorsString;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Set<Collaborator> getColaborators() {
		return colaborators;
	}

	public void setColaborators(Set<Collaborator> colaborators) {
		this.colaborators = colaborators;
	}
	
	public String getCollabortorsString() {
		return collabortorsString;
	}

	public void setCollabortorsString(String collabortorsString) {
		this.collabortorsString = collabortorsString;
	}

	public String administrativesString() {
		this.collabortorsString = "";
		if(!this.colaborators.isEmpty()) {
			for(Collaborator c : this.colaborators) {
				if(this.collabortorsString.isEmpty()) {
					this.collabortorsString = c.getName();
				} else {
					this.collabortorsString = this.collabortorsString + "; " + c.getName();
				}
			}
		}
		
		return this.collabortorsString.length() >= 33 ? 
				this.collabortorsString.substring(0, 33) + "..." : this.collabortorsString;
	}
	
	
}
