package br.com.gp.inventory.domain.enumeration;

public enum SoldCodeEnum {
	
	 SELLER('V', "Vendedor"),
	 SUPERVISOR('S', "Supervisor"),
	 MANAGER('G', "Gerente"),
	 REGIONAL('R', "Regional");
	
	private Character type;
	
	private String name;
	
	private SoldCodeEnum(Character type, String name) {
		this.type = type;
		this.name = name;
	}
	
	public Character type() {
		return this.type;
	}

	public String getName() {
		return this.name;
	}
}
