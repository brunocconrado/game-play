package br.com.gp.inventory.domain.enumeration;

public enum StatusEnum {
	
	ACTIVE(1),
	INACTIVE(2),
	PENDENT(3),
	PENDENT_MANAGER(4),
	APPROVED(5),
	REPROVED(6),
	SYSTEM_UPDATE(7);
	
	private Long id;
	
	private StatusEnum(long id) {
		this.id = id;
	}
	
	public Long id() {
		return this.id;
	}
	
	public static StatusEnum resolve(Long id) {
		StatusEnum solved = null;
		if(ACTIVE.id.equals(id)) {
			solved = ACTIVE;
		} else if(INACTIVE.id.equals(id)) {
			solved = INACTIVE;
		} else if(PENDENT.id.equals(id)) {
			solved = PENDENT;
		} else if(PENDENT_MANAGER.id.equals(id)) {
			solved = PENDENT_MANAGER;
		} else if(APPROVED.id.equals(id)) {
			solved = APPROVED;
		} else if(REPROVED.id.equals(id)) {
			solved = REPROVED;
		} else if(SYSTEM_UPDATE.id.equals(id)) {
			solved = SYSTEM_UPDATE;
		} else {
			throw new IllegalArgumentException("Status enum with id:" + " could not be found");
		}
		
		return solved;
	}

}
