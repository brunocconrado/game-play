package br.com.embracon.teamposition.domain.entity;

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
@Table(name = "INV_PROCESSADOR")
public class Processor implements br.com.embracon.j4e.domain.Entity {

	
	private static final long serialVersionUID = -8838851420440386488L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COD_PROCESSADOR", scale = 0, precision = 0)
	private Long id;
	
	@Column(name = "NOME", length = 80, nullable = false)
	private String name;
	
	@Column(name = "TITULO", length = 150, nullable = false)
	private String title;
	
	//TODO: Byte
	@Column(name = "DESCRIPTION", length = 100, nullable = false)
	private String description;
	
	//TODO: Byte
	@Column(name = "ESPECIFICATION", length = 100, nullable = false)
	private String especification;

	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FABRICANTE", nullable = false)
	private Manufacturer manufacturer;
	
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEspecification() {
		return especification;
	}

	public void setEspecification(String especification) {
		this.especification = especification;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}
	
}
