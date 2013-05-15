package br.com.embracon.teamposition.domain.enumeration;

public enum LogEnum {

	CREATED("CREATED"),
	UPDATED("UPDATED"),
	DELETED("DELETED");
	
	private String label;
	
	
	private LogEnum(String label) {
		this.label = label;
	}
	
	public String label() {
		return this.label;
	}
}
