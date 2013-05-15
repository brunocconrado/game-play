package br.com.embracon.teamposition.utils;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import br.com.embracon.teamposition.domain.entity.Collaborator;

public class FacesUtils {


	public static void invalidUserSession() {

		try {

			FacesContext context = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) 
					context.getExternalContext().getSession(false);
			session.removeAttribute(TeamPositionProperties.USER_LOGGED);
		} catch (Exception e) {
			// no-op
		}
	} 

	public static void addCallbackParam(String param, Object value) {
		RequestContext.getCurrentInstance().addCallbackParam(param, value);
	}


	public static String getRequestParameter(String key) {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get(key);
	}

	public static Object getObjectFromRequestParameter(
			String requestParameterName, 
			Converter converter, UIComponent component) {

		String theId = FacesUtils.getRequestParameter(requestParameterName);
		return converter.getAsObject(FacesContext
				.getCurrentInstance(),	component, theId);
	}

	public static void destroyBean(String name) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context
				.getExternalContext().getSession(false);
		session.removeAttribute(name);
	}

	public static String getSessionParam(String param) {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) 
				context.getExternalContext().getSession(false);

		String value = (String) session.getAttribute(param);
		session.removeAttribute(param);

		return value;
	}

	public static Collaborator getCollaborator() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) 
				context.getExternalContext().getSession(false);
		return (Collaborator) session.getAttribute(TeamPositionProperties.USER_LOGGED);
	}

	public static void redirect(String page) {
		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		try {
			String path = ((HttpServletRequest) ec.getRequest())
					.getRequestURI();
			if (!page.equals(path.substring(15, path.length() - 6))) {
				ec.redirect(ec.getRequestContextPath() + "/faces" + page
						+ ".xhtml");
			}
		} catch (IOException ex) {
			/**
			 * @TODO: Log
			 */
		}
	}

}
