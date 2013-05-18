package br.com.gp.inventory.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INV_CATEGORIA")
public class Category implements br.com.embracon.j4e.domain.Entity {

	private static final long serialVersionUID = -5668217413897460651L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COD_CATEGORIA", scale = 0, precision = 0)
	private Long id;
	
	@Column(name = "NAME", length = 10)
	private String name;

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
	
}
