package br.com.gp.inventory.domain.repository;

import br.com.embracon.j4e.domain.repository.Repository;
import br.com.gp.inventory.domain.entity.Socket;

public interface SocketRepository extends Repository<Socket> {

	public Socket findByName(String name);

}
