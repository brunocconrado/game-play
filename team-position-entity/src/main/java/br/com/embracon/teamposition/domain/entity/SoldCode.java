package br.com.embracon.teamposition.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * V - SELLER
 * S - SUPERVISOR
 * G - MANAGER
 * R - REGIONAL
 * 
 * @author bruno.conrado
 *
 */
@Entity(name = "TP_CODIGO_VENDA")
@Table(name = "TP_CODIGO_VENDA")
public class SoldCode implements br.com.embracon.j4e.domain.Entity {

	private static final long serialVersionUID = -2865567676114364917L;
	
	public static final Long DIAMANT = 5L;

	@Id
	@Column(name = "COD_CODIGO_VENDA", scale = 0, nullable = false)
	private Long id;
	
	@Column(name = "CODIGO", length = 3, nullable = false)
	private String code;
	
	@Column(name = "NOME", length = 50, nullable = false)
	private String name;

	@Column(name = "TIPO", length = 1, nullable = false)
	private Character type;
	
	@Column(name = "STATUS", length = 1, nullable = false)
	private Character status;

	
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

	public Character getType() {
		return type;
	}

	public void setType(Character type) {
		this.type = type;
	}
	
	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public int hashCode() {
		return this.id != null ? this.id.hashCode() : 0;
	}
	
	public boolean equals(Object obj) {
		return obj instanceof SoldCode && this.equals((SoldCode)obj);
	}
	
	public boolean equals(SoldCode other) {
		return this.id != null && this.id.equals(other.id);
	}
}
