package br.com.embracon.teamposition.domain.search;

public class CollaboratorSearch {

	private String cpf;
	
	private String registry;

	private String email;
	
	public String getCpf() {
		return cpf;
	} 

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRegistry() {
		return registry;
	}

	public void setRegistry(String registry) {
		this.registry = registry;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getLogin() {
		int index = this.email.indexOf("@");
		return this.email.substring(0, index).toLowerCase();
	}
	
	public String getCnpj(){
		return null;
	}
}
