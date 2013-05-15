package br.com.embracon.teamposition.domain.repository.impl;

import org.springframework.stereotype.Repository;

import br.com.embracon.teamposition.domain.entity.AtaExcecao;
import br.com.embracon.teamposition.domain.repository.AtaExcecaoRepository;

@Repository("ataExcecaoRepository")
public class AtaExcecaoRepositoryImpl extends AbstractHibernateRepostirory<AtaExcecao> implements AtaExcecaoRepository {

	private static final long serialVersionUID = 7276154313600590992L;

	public AtaExcecaoRepositoryImpl() {
		super(AtaExcecao.class);
	}

}
