package br.com.embracon.teamposition.domain.service;

import java.util.List;


import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.embracon.teamposition.domain.entity.Socket;

public interface SocketService {
	
	public List<Socket> findAll() throws ServiceException;

}
