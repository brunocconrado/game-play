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
@Table(name = "INV_HD")
public class HardDisk implements br.com.embracon.j4e.domain.Entity {
	

	private static final long serialVersionUID = 7491040935679457890L;

	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,###,###,###,##0.00");
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COD_FREQUENCIA", scale = 0, precision = 0)
	private Long id;
	
	@Column(name = "NAME", length = 40)
	private String name;
	
	@Column(name = "CODIGO", length = 10, nullable = false)
	private String code;
	
	@Column(name = "CAPACITY", length = 6, nullable = false)
	private String capacity;
	
	@Column(name = "TITULO", length = 150, nullable = false)
	private String title;
	
	@Column(name = "WATTS", length = 10)
	private String watts;
	
	@Lob
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;
	
	@Lob
	@Column(name = "ESPECIFICATION", nullable = false)
	private String especification;
	
	@Column(name = "PRECO", precision = 10, scale = 2, nullable = false)
	private BigDecimal price;
	
	@Column(name = "SSD")
	private boolean ssd;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FABRICANTE", nullable = true)
	private Manufacturer manufacturer;
	
	@Transient
	private String priceString;
	
	public HardDisk() {
		this.manufacturer = new Manufacturer();
	}
	
	public HardDisk(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
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
	
	public String getWatts() {
		return watts;
	}

	public void setWatts(String watts) {
		this.watts = watts;
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

	public boolean isSsd() {
		return ssd;
	}

	public void setSsd(boolean ssd) {
		this.ssd = ssd;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
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
	
	public String getSsdString() {
		return this.ssd ? "Sim" : "Não";
	}
	
	public boolean equals(Object obj) {
		return obj instanceof HardDisk && this.equals((HardDisk)obj);
	}
	
	public boolean equals(HardDisk other) {
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
			.append(" GB").append(this.isSsd() ? " SSD - " : " - ")
			.append(this.manufacturer.getName())
			.append(" - R$ ")
			.append(this.getPriceString())
			.toString();
	}
}
