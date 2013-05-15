package br.com.embracon.teamposition.domain.enumeration;

public enum SituationEnum {
	
	INVALID_SITUATION(-1),
	ACTIVE(1),
	FIRED(2),
	RETURN(3),
	TRANFER(5), 
	ABSENCE(6),
	VACATIONS(8);
	
	private Long id;
	
	private SituationEnum(long id) {
		this.id = id;
	}
	
	public Long id() {
		return this.id;
	}

}
