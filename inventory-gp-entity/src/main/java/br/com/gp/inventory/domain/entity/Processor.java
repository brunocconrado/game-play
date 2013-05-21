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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "INV_PROCESSADOR")
public class Processor implements br.com.embracon.j4e.domain.Entity {

	
	private static final long serialVersionUID = -8838851420440386488L;
	
	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,###,###,###,##0.00");

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COD_PROCESSADOR", scale = 0, precision = 0)
	private Long id;
	
	@Column(name = "CODIGO", length = 10, nullable = false)
	private String code;
	
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
	
	@Column(name = "PRECO", scale = 10, precision = 2)
	private BigDecimal price;

	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FABRICANTE", nullable = false)
	private Manufacturer manufacturer;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SOCKET", nullable = false)
	private Socket socket;
	
	@Transient
	private String priceString;
	
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

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
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
		return obj instanceof Processor && this.equals((Processor)obj);
	}
	
	public boolean equals(Processor other) {
		return 	this.id != null && this.id.equals(other.id);
	}
	
	public int hashCode() {
		return this.id != null ? this.id.hashCode() : 0;
	}
	
}
