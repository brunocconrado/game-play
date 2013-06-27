package br.com.gp.inventory.domain.repository.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.gp.inventory.domain.entity.Socket;
import br.com.gp.inventory.domain.repository.SocketRepository;

@Repository("socketRepository")
public class SocketRepositoryImpl extends AbstractHibernateRepostirory<Socket> implements SocketRepository {

	private static final long serialVersionUID = -1688778425972502385L;

	public SocketRepositoryImpl() {
		super(Socket.class);
	}

	@Override
	public Socket findByName(String name) {
		return (Socket) this.createCriteria().add(Restrictions.eq("name", name)).uniqueResult();
	}

}
