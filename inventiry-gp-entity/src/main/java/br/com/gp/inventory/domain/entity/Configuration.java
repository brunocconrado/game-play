package br.com.gp.inventory.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity(name = "TP_CONFIGURACOES")
@Table(name = "TP_CONFIGURACOES")
public class Configuration implements br.com.embracon.j4e.domain.Entity {

	
	private static final long serialVersionUID = 4658349312970720518L;

	@Id
	@Column(name = "COD_CONFIGURACOES", scale = 0, precision = 0)
	private Long id;
	
	@Column(name = "CLASS_NAME", length = 150, nullable = false)
	private String className;
	
	@Column(name = "TIPO", length = 80, nullable = false)
	private String type;
	
	@Column(name = "VALOR", length = 100, nullable = false)
	private String value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public Integer getIntegerValue() {
		return Integer.parseInt(this.value.toString().trim());
	}
}
