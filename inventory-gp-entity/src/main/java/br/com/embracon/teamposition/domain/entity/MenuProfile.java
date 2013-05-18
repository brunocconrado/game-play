package br.com.embracon.teamposition.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TP_MENU_PERFIL_MF") 
public class MenuProfile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COD_MENU_PERFIL", scale = 0, precision = 0)
	private Long id;
	
	@Column(name = "COD_PERFIL", scale = 0, precision = 0, nullable = false)
	private Long profileId;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COD_MENU", nullable = false)
	private Menu menu;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
}
