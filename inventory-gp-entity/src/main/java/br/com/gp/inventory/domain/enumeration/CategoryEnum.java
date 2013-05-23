package br.com.gp.inventory.domain.enumeration;

public enum CategoryEnum {
	
	PROCESSOR(1L), 
	MOTHERBOARD(2L),
	MEMORY(3L), 
	VIDEO_CARD(4L),
	HD(5L), 
	TOWER(6L),
	DRIVE(7L);
	
	private Long value;
	
	private CategoryEnum(Long value) {
		this.value = value;
	}
	
	public Long value() {
		return this.value;
	}

}
