package br.com.gp.inventory.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "INV_FREQUENCIA")
public class Frequency implements br.com.embracon.j4e.domain.Entity {
	
	private static final long serialVersionUID = 3800121673790112962L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COD_FREQUENCIA", scale = 0, precision = 0)
	private Long id;
	
	@Column(name = "NAME", length = 40)
	private String name;

	public Frequency() {}
	
	public Frequency(String name) {
		this.name = name;
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
	
}
