package br.com.embracon.teamposition.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The sistuations has a type to identify what registry it is used.
 * The types can be:
 * V - SELLER
 * S - SUPERVISOR
 * B - BOTH
 * 
 * @author Conrado
 *
 */

@Entity(name = "TP_SITUACAO")
@Table(name = "TP_SITUACAO")
public class Situation implements br.com.embracon.j4e.domain.Entity {

	
	private static final long serialVersionUID = -6260357633617417516L;

	
	public static final Character SELLER = 'V';
	public static final Character SUPERVISOR = 'S';
	public static final Character BOTH = 'B';
	
	@Id
	@Column(name = "COD_SITUACAO", scale = 0, precision = 0)
	private Long id;
	
	@Column(name = "NOME", length = 40, nullable = false)
	private String name;
	
	/**
	 * This type can be V, S or B
	 */
	@Column(name = "TIPO", length = 1, nullable = false)
	private Character type;

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

	public Character getType() {
		return type;
	}

	public void setType(Character type) {
		this.type = type;
	}
	
	public int hashCode() {
		return this.id != null ? this.id.hashCode() : 0;
	}
	
	public boolean equals(Object obj) {
		return obj instanceof Situation && this.equals((Situation)obj);
	}
	
	public boolean equals(Situation other) {
		return this.id != null && this.id.equals(other.id);
	}
}
