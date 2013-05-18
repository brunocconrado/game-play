package br.com.embracon.teamposition.domain.service.impl;

import java.util.List;

import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.embracon.teamposition.domain.entity.Socket;
import br.com.embracon.teamposition.domain.repository.SocketRepository;
import br.com.embracon.teamposition.domain.service.SocketService;

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

}
