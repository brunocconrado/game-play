package br.com.gp.inventory.domain.service.impl;

import java.util.List;

import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.gp.inventory.domain.entity.Socket;
import br.com.gp.inventory.domain.repository.SocketRepository;
import br.com.gp.inventory.domain.service.SocketService;

@Component("socketService")
@Interceptors({ServiceInteceptor.class})
public class SocketServiceImpl implements SocketService {
	
	@Autowired
	@Qualifier("socketRepository")
	private SocketRepository repository;
	
	@Override
	public List<Socket> findAll() throws ServiceException {
		return (List<Socket>) repository.findAll();
	}

	@Override
	public Socket findById(Long socketId) throws ServiceException {
		return this.repository.findByIdentity(socketId);
	}

}
