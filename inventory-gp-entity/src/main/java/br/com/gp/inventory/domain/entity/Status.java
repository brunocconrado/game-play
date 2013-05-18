package br.com.gp.inventory.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "TP_STATUS")
@Table(name = "TP_STATUS")
public class Status implements br.com.embracon.j4e.domain.Entity, Comparable<Status> {

	
	private static final long serialVersionUID = 9035979048866556229L;
	
	public static final long ACTIVE = 1;
	public static final long INACTIVE = 2;
	

	@Id
	@Column(name = "COD_STATUS", scale = 0, precision = 0)
	private Long id;
	
	@Column(name = "NOME", length = 30, nullable = false)
	private String name;
	
	@Column(name = "SIGLA", length = 2, nullable = false)
	private String acronym;
	
	@Column(name = "ATIVO", length = 1, nullable = false)
	private Character active = 'S';

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

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public Character getActive() {
		return active;
	}

	public void setActive(Character active) {
		this.active = active;
	}

	@Override
	public int compareTo(Status other) {
		return this.name.compareTo(other.name);
	}
	
}
