package br.com.embracon.teamposition.domain.vo;

import java.util.HashSet;
import java.util.Set;

import br.com.embracon.teamposition.domain.enumeration.ProfileEnum;

public class UserSession {
	
	private Integer registry;
	
	private String name;
	
	private String login;

	private Set<ProfileEnum> profiles = new HashSet<ProfileEnum>();
	
	private Set<String> beans = new HashSet<String>();
	
	public Integer getRegistry() {
		return registry;
	}

	public void setRegistry(Integer registry) {
		this.registry = registry;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Set<ProfileEnum> getProfiles() {
		return profiles;
	}

	public void setProfiles(Set<ProfileEnum> profiles) {
		this.profiles = profiles;
	}
	
	public void addProfile(ProfileEnum profile) {
		this.profiles.add(profile);
	}

	public boolean isCommercial() {
		return this.profiles.contains(ProfileEnum.COMMERCIAL);
	}
	
	public boolean isManager() {
		return this.profiles.contains(ProfileEnum.MANAGER);
	}
	
	public boolean isAdm() {
		return !isCommercial() && !isManager();
	}

	public Set<String> getBeans() {
		return beans;
	}
	
	public void addBean(String name) {
		this.beans.add(name);
	}
	
}
