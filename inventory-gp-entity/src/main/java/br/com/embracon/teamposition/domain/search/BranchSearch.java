package br.com.embracon.teamposition.domain.search;

public class BranchSearch {

	private String code;

	private String name;

	private String aRegionalCode;

	private String aRegionalName;

	private String dirCode;

	private String register;
	
	private String regionalRegister;

	private String managerRegister;

	private String regionalName;

	private String managerCode;

	private String managerName;

	private String fieldSupervisorRegistry;

	private String cpf;

	private String cnpj;
	
	private String email;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getaRegionalCode() {
		return aRegionalCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setaRegionalCode(String aRegionalCode) {
		this.aRegionalCode = aRegionalCode;
	}

	public String getaRegionalName() {
		return aRegionalName;
	}

	public void setaRegionalName(String aRegionalName) {
		this.aRegionalName = aRegionalName;
	}

	public String getRegister() {
		return register;
	}
 
	public void setRegister(String register) {
		this.register = register;
	}

	public String getDirCode() {
		return dirCode;
	}

	public void setDirCode(String dirCode) {
		this.dirCode = dirCode;
	}

	public String getRegionalRegister() {
		return regionalRegister;
	}

	public void setRegionalRegister(String regionalRegister) {
		this.regionalRegister = regionalRegister;
	}

	public String getManagerRegister() {
		return managerRegister;
	}

	public void setManagerRegister(String managerRegister) {
		this.managerRegister = managerRegister;
	}

	public String getRegionalName() {
		return regionalName;
	}

	public void setRegionalName(String regionalName) {
		this.regionalName = regionalName;
	}

	public String getManagerCode() {
		return managerCode;
	}

	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getFieldSupervisorRegistry() {
		return fieldSupervisorRegistry;
	}

	public void setFieldSupervisorRegistry(String fieldSupervisorRegistry) {
		this.fieldSupervisorRegistry = fieldSupervisorRegistry;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return email != null ? email.substring(0, email.indexOf("@")) : null;
	}
}
