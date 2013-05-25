package br.com.gp.inventory.domain.repository;

import java.util.List;

import br.com.gp.inventory.domain.entity.Processor;
import br.com.gp.inventory.domain.entity.Socket;

public interface ProcessorRepository extends br.com.embracon.j4e.domain.repository.Repository<Processor> {

	public List<Processor> findBySocket(Socket socket);

}
