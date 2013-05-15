package br.com.embracon.teamposition.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import br.com.embracon.j4e.i18n.Messages;
import br.com.embracon.j4e.logging.Logger;
import br.com.embracon.j4e.services.exception.ServiceException;
import br.com.embracon.j4e.validation.ValidationException;
import br.com.embracon.teamposition.domain.service.LoginService;
import br.com.embracon.teamposition.domain.vo.UserSession;
import br.com.embracon.teamposition.utils.TeamPositionProperties;

@Component("loginServlet")
public class LoginServlet implements HttpRequestHandler {

	@Autowired
	@Qualifier("loginService")
	private LoginService service;

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {

			UserSession user = (UserSession) request.getSession()
					.getAttribute(TeamPositionProperties.USER_LOGGED);

			if(user != null) {
				invalidateSession(request, user);
			}

			user = this.service.autenticate(
					request.getParameter("authToken"),
					request.getRemoteAddr(),
					request.getServerName());


			request.getSession().invalidate();

			request.getSession().removeAttribute(TeamPositionProperties.USER_LOGGED);
			request.getSession().setAttribute(TeamPositionProperties.USER_LOGGED, user);
			request.getRequestDispatcher("redirect.html").include(request, response);

		} catch (ValidationException e) {
			request.getSession().setAttribute("errorMessage", e.getMessage());
			response.sendRedirect("faces/error/error.xhtml");
		} catch (ServiceException e) {
			Logger.getLogger().error(Messages.getMessage("error.user.login"), e);
			request.getSession().setAttribute("errorMessage", Messages.getMessage("error.user.login"));
			response.sendRedirect("faces/error/error.xhtml");
		}

	}

	private void invalidateSession(HttpServletRequest request, UserSession user) {
		for(String bean : user.getBeans()) {
			request.removeAttribute(bean);
		}

	} 
}
