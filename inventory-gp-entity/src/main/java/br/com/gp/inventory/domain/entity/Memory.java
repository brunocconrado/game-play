package br.com.gp.inventory.domain.entity;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "INV_MEMORIA")
public class Memory  implements br.com.embracon.j4e.domain.Entity {

	
	private static final long serialVersionUID = 6218559424319113571L;

	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,###,###,###,##0.00");
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COD_MEMORIA", scale = 0, nullable = false)
	private Long id;
	
	@Column(name = "CODIGO", length = 10, nullable = false)
	private String code;
	
	@Column(name = "CAPACITY", length = 2, nullable = false)
	private String capacity;
	
	@Column(name = "NOME", length = 80, nullable = false)
	private String name;
	
	@Column(name = "TITULO", length = 150, nullable = false)
	private String title;
	
	@Lob
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;
	
	@Lob
	@Column(name = "ESPECIFICATION", nullable = false)
	private String especification;
	
	@Column(name = "PRECO", precision = 10, scale = 2)
	private BigDecimal price;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FABRICANTE", nullable = false)
	private Manufacturer manufacturer;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FREQUENCIA", nullable = false)
	private Frequency frequency;
	
	@Transient
	private String priceString;

	public Memory() {
		this.manufacturer = new Manufacturer();
		this.frequency = new Frequency();
	}
	
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

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Frequency getFrequency() {
		return frequency;
	}

	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}

	public String getPriceString() {
		if(priceString == null && price != null) {
			this.priceString = DECIMAL_FORMAT.format(this.price);
		}
		return priceString;
	}

	public void setPriceString(String priceString) {
		this.priceString = priceString;
		this.price = new BigDecimal(priceString.replace(".", "").replace(",", "."));
	}
	
	public boolean equals(Object obj) {
		return obj instanceof Memory && this.equals((Memory)obj);
	}
	
	public boolean equals(Memory other) {
		return 	this.id != null && this.id.equals(other.id);
	}
	
	public int hashCode() {
		return this.id != null ? this.id.hashCode() : 0;
	}
	
	public String getToString() {
		return this.toString();
	}
	
	public String toString() {
		return new StringBuilder()
			.append(this.code)
			.append(" - ")
			.append(this.name)
			.append(" - ")
			.append(this.capacity)
			.append(" GB - ")
			.append(this.manufacturer.getName())
			.append(" - R$ ")
			.append(this.getPriceString())
			.toString();
	}
	
}
