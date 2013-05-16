package br.com.embracon.teamposition.domain.enumeration;

import br.com.embracon.j4e.i18n.Messages;


public enum ProfileEnum {
	
	MANAGER(Long.parseLong(Messages.getMessage("profile.manager"))),
	ADM(Long.parseLong(Messages.getMessage("profile.adm"))),
	COMMERCIAL(Long.parseLong(Messages.getMessage("profile.commercial")));

	private Long id;
	
	private String label;
	
	private ProfileEnum(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public String getName() {
		return label;
	}
	
	public static ProfileEnum resolve(Long id, String name) {
		
		ProfileEnum profileEnum = null;
		if(MANAGER.id.equals(id)) {
			profileEnum = MANAGER;
		} else if(ADM.id.equals(id)) {
			profileEnum = ADM;
		} else if(COMMERCIAL.id.equals(id)) {
			profileEnum = COMMERCIAL;
		}
		
		profileEnum.label = name;
		
		return profileEnum;
	}
	
}
