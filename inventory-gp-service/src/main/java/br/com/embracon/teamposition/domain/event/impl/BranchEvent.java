package br.com.embracon.teamposition.domain.event.impl;

import br.com.embracon.teamposition.domain.entity.tmp.Branch;
import br.com.embracon.teamposition.domain.event.Event;
import br.com.embracon.teamposition.domain.vo.UserSession;

public class BranchEvent implements Event<Branch> {

	private Branch branch;
	
	private UserSession editor;

	public BranchEvent(Branch branch, UserSession editor) {
		this.branch = branch;
		this.editor = editor;
	}

	@Override
	public Branch getEvent() {
		return this.branch;

	}
	
	@Override
	@SuppressWarnings("unchecked")
	public UserSession getEditor() {
		return this.editor;
	}
}