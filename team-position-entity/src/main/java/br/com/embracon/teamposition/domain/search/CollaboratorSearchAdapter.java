package br.com.embracon.teamposition.domain.search;

public class CollaboratorSearchAdapter extends CollaboratorSearch {

	
	private BranchSearch search;
	
	@SuppressWarnings("unused")
	private CollaboratorSearchAdapter() {}
	
	public CollaboratorSearchAdapter(BranchSearch search) {
		this.search = search;
	}

	@Override
	public String getCpf() {
		return this.search.getCpf();
	}

	@Override
	public String getEmail() {
		return this.search.getEmail();
	}

	@Override
	public String getLogin() {
		return this.search.getLogin();
	}
	
	public String getCnpj() {
		return this.search.getCnpj();
	}
	
	
}
