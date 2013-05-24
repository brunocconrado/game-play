package br.com.gp.inventory.domain.repository.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.gp.inventory.domain.entity.Font;
import br.com.gp.inventory.domain.repository.FontRepository;

@Repository("fontRepository")
@Transactional(propagation = Propagation.REQUIRED)
public class FontRepositoryImpl extends AbstractHibernateRepostirory<Font> implements FontRepository {

	private static final long serialVersionUID = 3454732409836461229L;

	public FontRepositoryImpl() {
		super(Font.class);
	}

}
