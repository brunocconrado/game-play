package br.com.embracon.teamposition.domain.enumeration;

public enum ConfigurationEnum {
	
	SUPERVISOR_TEAM("AMOUNT_SUPERVISOR_TEAM"), 
	ABSENCE_DAYS("AMOUNT_ABSENCE_DAYS"), 
	DAYS_AFTER_CONCLUDE_REGISTRY("DAYS_AFTER_CONCLUDE_REGISTRY"), 
	DAYS_CLONE_TEAM_POSITION("DAYS_CLONE_TEAM_POSITION");
	
	private String value;
	
	private ConfigurationEnum(String value) {
		this.value = value;
	}
	
	public String value() {
		return this.value;
	}

}
