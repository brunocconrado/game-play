package br.com.gp.inventory.domain.enumeration;

public enum CategoryEnum {
	
	PROCESSOR(1L), 
	MOTHERBOARD(2L),
	MEMORY(3L), 
	VIDEO_CARD(4L),
	HD(-1L), 
	CASE(-1L);
	
	private Long value;
	
	private CategoryEnum(Long value) {
		this.value = value;
	}
	
	public Long value() {
		return this.value;
	}

}
