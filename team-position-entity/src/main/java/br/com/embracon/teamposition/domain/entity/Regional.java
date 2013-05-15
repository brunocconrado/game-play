package br.com.embracon.teamposition.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "TP_REGIONAL")
@Table(name = "TP_REGIONAL")
public class Regional implements br.com.embracon.j4e.domain.Entity {
	
	
	private static final long serialVersionUID = 1139046502554120889L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_TP_REGIONAL")
	@SequenceGenerator(name = "SEQ_TP_REGIONAL", sequenceName = "SEQ_TP_REGIONAL")
	@Column(name = "COD_REGIONAL", scale = 0, precision = 0)
	private Long id;
	
	@Column(name = "NOME", length = 80, nullable = false)
	private String name;
	
	@Column(name = "CODIGO", length = 10, nullable = false)
	private String code;
	
	@Column(name = "APELIDO", length = 60)
	private String apelido;
	
	@Column(name = "CODIGO_DIRETOR", length = 10)
	private String directorCode;
	
	@Column(name = "NOME_DIRETOR", length = 80)
	private String directorName;
	
	@Column(name = "CODIGO_UNIDADE", length = 10)
	private String unitCode;
	
	@Column(name = "NOME_UNIDADE", length = 80)
	private String unitName;

	public Regional() {}
	
	public Regional(String code, String name) {
		this.code = code;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getDirectorCode() {
		return directorCode;
	}

	public void setDirectorCode(String directorCode) {
		this.directorCode = directorCode;
	}

	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

}
