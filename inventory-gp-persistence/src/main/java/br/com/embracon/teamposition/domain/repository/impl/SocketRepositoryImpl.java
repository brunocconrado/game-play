package br.com.embracon.teamposition.domain.repository.impl;

import org.springframework.stereotype.Repository;

import br.com.embracon.j4e.domain.repository.AbstractRepository;
import br.com.embracon.teamposition.domain.entity.Socket;
import br.com.embracon.teamposition.domain.repository.SocketRepository;

@Repository("socketRepository")
public class SocketRepositoryImpl extends AbstractRepository<Socket> implements SocketRepository {

	public SocketRepositoryImpl() {
		super(Socket.class);
	}


}
