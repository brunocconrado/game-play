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
@Table(name = "INV_INVENTARIO")
public class Inventory implements br.com.embracon.j4e.domain.Entity {

	private static final long serialVersionUID = -4023803462716977747L;

	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,###,###,###,##0.00");

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COD_FREQUENCIA", scale = 0, precision = 0)
	private Long id;

	@Column(name = "NAME", length = 40)
	private String name;

	@Column(name = "CODIGO", length = 10, nullable = false)
	private String code;

	@Column(name = "PRECO", precision = 10, scale = 2, nullable = false)
	private BigDecimal price;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PLACA_MAE", nullable = true)
	private Motherboard motherboard;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PROCESSADOR", nullable = true)
	private Processor processor;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MEMORIA", nullable = true)
	private Memory memory;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "HD_SSD", nullable = true)
	private HardDisk hardDisk;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DRIVE", nullable = true)
	private Drive drive;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PLACA_VIDEO", nullable = true)
	private VideoCard videoCard;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FONTE", nullable = true)
	private Font font;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TORRE", nullable = true)
	private Tower tower;

	@Transient
	private String priceString;

	public Inventory() {
		this.motherboard = new Motherboard();
		this.processor = new Processor();
		this.memory = new Memory();
		this.hardDisk = new HardDisk();
		this.drive = new Drive();
		this.videoCard = new VideoCard();
		this.font = new Font();
		this.tower = new Tower();
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Motherboard getMotherboard() {
		return motherboard;
	}

	public void setMotherboard(Motherboard motherboard) {
		this.motherboard = motherboard;
	}

	public Processor getProcessor() {
		return processor;
	}

	public void setProcessor(Processor processor) {
		this.processor = processor;
	}

	public Memory getMemory() {
		return memory;
	}

	public void setMemory(Memory memory) {
		this.memory = memory;
	}

	public HardDisk getHardDisk() {
		return hardDisk;
	}

	public void setHardDisk(HardDisk hardDisk) {
		this.hardDisk = hardDisk;
	}

	public Drive getDrive() {
		return drive;
	}

	public void setDrive(Drive drive) {
		this.drive = drive;
	}

	public VideoCard getVideoCard() {
		return videoCard;
	}

	public void setVideoCard(VideoCard videoCard) {
		this.videoCard = videoCard;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Tower getTower() {
		return tower;
	}

	public void setTower(Tower tower) {
		this.tower = tower;
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
		return obj instanceof Inventory && this.equals((Inventory)obj);
	}

	public boolean equals(Inventory other) {
		return 	this.id != null && this.id.equals(other.id);
	}

	public int hashCode() {
		return this.id != null ? this.id.hashCode() : 0;
	}

}
