package br.com.gp.inventory.domain.search;

public class TeamSellerSearch {
	
	private String supervisorName;
	
	private String sellerName;
	
	private String supervisorRegistry;
	
	private String sellerRegsitry;
	
	private Long branchId;
	
	private Long ataId;
	
	private Long statusId;

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getSupervisorRegistry() {
		return supervisorRegistry;
	}

	public void setSupervisorRegistry(String supervisorRegistry) {
		this.supervisorRegistry = supervisorRegistry;
	}

	public String getSellerRegsitry() {
		return sellerRegsitry;
	}

	public void setSellerRegsitry(String sellerRegsitry) {
		this.sellerRegsitry = sellerRegsitry;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public Long getAtaId() {
		return ataId;
	}

	public void setAtaId(Long ataId) {
		this.ataId = ataId;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	

}
