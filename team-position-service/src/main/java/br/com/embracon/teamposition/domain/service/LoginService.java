package br.com.embracon.teamposition.domain.service;

import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.embracon.teamposition.domain.vo.UserSession;

public interface LoginService {

	public UserSession autenticate(String login, String remoteAddr, String serverName) throws ServiceException;
	
}
