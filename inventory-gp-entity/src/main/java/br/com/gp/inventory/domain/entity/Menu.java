package br.com.gp.inventory.domain.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "INV_MENU") 
public class Menu implements br.com.embracon.j4e.domain.Entity {

	private static final long serialVersionUID = 3923204993448223460L;

	@Id
	@Column(name = "COD_MENU", scale = 0, nullable = false)
	private Long id;
	
	@Column(name = "NOME", length = 40, nullable = false)
	private String value;
	
	@Column(name = "IMAGEM", length = 60)
	private String icon;
	
	@Column(name = "URL", length = 80)
	private String url;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COD_PARENTE")
	private Menu parent;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "parent")
	private Set<Menu> children = new HashSet<Menu>();
	
	@Transient
	private Long profileId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	} 
	
	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	public Set<Menu> getChildren() {
		return children;
	}

	public void setChildren(Set<Menu> children) {
		this.children = children;
	}

	public boolean hasChildren() {
		return !this.children.isEmpty();
	}
	
	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Menu && this.equals((Menu) obj);
	}

	private boolean equals(Menu other) {
		return this.id != null && this.id.equals(other.id);
	}

	@Override
	public int hashCode() {
		return this.id == null ? 1 : this.id.hashCode();
	}
	
	
}
