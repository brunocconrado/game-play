package br.com.gp.inventory.domain.enumeration;

public enum CategoryEnum {
	
	PROCESSOR(2L), 
	MEMORY(3L), 
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
