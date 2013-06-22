package br.com.gp.inventory.domain.service;

import java.util.List;


import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Socket;

public interface SocketService {
	
	public List<Socket> findAll() throws ServiceException;

	public Socket findById(Long socketId) throws ServiceException;

	public Socket findByName(String name) throws ServiceException;

	public void save(Socket socket) throws ServiceException;

	public Socket findOrCreateByName(String trim) throws ServiceException;

}
