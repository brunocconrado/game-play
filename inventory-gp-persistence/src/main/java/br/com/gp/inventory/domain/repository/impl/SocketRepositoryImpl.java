package br.com.gp.inventory.domain.repository.impl;

import org.springframework.stereotype.Repository;

import br.com.embracon.j4e.domain.repository.AbstractRepository;
import br.com.gp.inventory.domain.entity.Socket;
import br.com.gp.inventory.domain.repository.SocketRepository;

@Repository("socketRepository")
public class SocketRepositoryImpl extends AbstractRepository<Socket> implements SocketRepository {

	public SocketRepositoryImpl() {
		super(Socket.class);
	}


}
