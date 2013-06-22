package br.com.gp.inventory.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "INV_FABRICANTE")
public class Manufacturer implements br.com.embracon.j4e.domain.Entity {

	private static final long serialVersionUID = -6410183607315438247L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COD_FABRICANTE", scale = 0, nullable = false)
	private Long id;
	
	@Column(name = "NOME", length = 80, nullable = false)
	private String name;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CATEGORIA", nullable = false)
	private Category category;
	
	public Manufacturer() {}
	
	public Manufacturer(String name) {
		this.name = name 
	}

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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
}
