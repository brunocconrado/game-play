package br.com.gp.inventory.domain.bean;

import java.util.Collection;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import br.com.embracon.j4e.i18n.Messages;
import br.com.embracon.j4e.validation.InvalidationReason;
import br.com.embracon.j4e.validation.LocalizableReason;
import br.com.gp.inventory.domain.vo.UserSession;
import br.com.gp.inventory.utils.FacesUtils;
import br.com.gp.inventory.utils.TeamPositionProperties;

public class DefaultBean {
	
	protected DefaultBean(String name) {
		addBean(name);
	}
	
	private void addBean(String name) {
		
		UserSession user = ((UserSession)
				getFromSession(TeamPositionProperties.USER_LOGGED));
		user.addBean(name);
		
		addInSession(user, TeamPositionProperties.USER_LOGGED);
	}
	
	protected String getLoginUserInSession() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) 
				context.getExternalContext().getSession(false);
		return (String) session.getAttribute(TeamPositionProperties.USER_LOGIN);
	}
	
	protected void addInSession(Object obj, String key) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) 
				context.getExternalContext().getSession(false);
		session.setAttribute(key, obj);
	}
	
	protected Object getFromSession(String key) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) 
				context.getExternalContext().getSession(false);
		return session.getAttribute(key);
	}
	
	protected void addCallbackParam(String param, Object value) {
		RequestContext.getCurrentInstance().addCallbackParam(param, value);
	}

	protected void successMessage(String msg) {
		successMessage(msg, "");
	}

	protected void successMessage(String keyMsg, Object... params) {
		FacesContext.getCurrentInstance().addMessage(null, 
				createMessage(FacesMessage.SEVERITY_INFO, keyMsg, keyMsg, true, params));
	}

	protected void warningMessage(Collection<InvalidationReason> reasons) {
		for(InvalidationReason reason : reasons) {
			LocalizableReason localizableMessage = (LocalizableReason)reason;
			warningMessage(localizableMessage.getLabel(), localizableMessage.getParams());
		}
	}
	
	protected void warningMessage(String keyMsg) {
		warningMessage(keyMsg, keyMsg);
	}
	
	protected void warningMessage(String keyMsg, Object... params) {
		FacesContext.getCurrentInstance().addMessage(null, 
				createMessage(FacesMessage.SEVERITY_WARN, keyMsg, true, params));
	}
	
	protected void warningMessage(String keyMsg, String keyDetail) {
		FacesContext.getCurrentInstance().addMessage(null, 
				createMessage(FacesMessage.SEVERITY_WARN, keyMsg, keyDetail));
	}
	
	protected void errorMessages(List<String> messages) {
		for (String message : messages) {
			errorMessage(message);
		}
	}

	protected void errorMessage(String keyMsg) {
		FacesContext.getCurrentInstance().addMessage(null, 
				createMessage(FacesMessage.SEVERITY_ERROR, keyMsg, keyMsg));
	}

	protected void errorMessage(String keyMsg, Throwable t) {
		FacesContext.getCurrentInstance().addMessage(null, 
				createMessage(FacesMessage.SEVERITY_ERROR, keyMsg, keyMsg));

		//TODO: Arruamr log
		//Logger.getLogger().error(msg, t);
	}

	protected void errorMessage(String keyMsg, Object... strings) {
		FacesContext.getCurrentInstance().addMessage(null, 
				createMessage(FacesMessage.SEVERITY_ERROR, keyMsg, keyMsg, strings));
	}
	
	protected void errorMessage(String keyMsg, Throwable t, Object... strings) {
		FacesContext.getCurrentInstance().addMessage(null, 
				createMessage(FacesMessage.SEVERITY_ERROR, keyMsg, keyMsg, strings));
		
		t.printStackTrace();
		//TODO: Abilitar Log
		//Logger.getLogger().error(msg, t);
	}
	
	protected void fatalMessage(String keyMsg) {
		fatalMessage(keyMsg, keyMsg);
	}
	
	protected void fatalMessage(String keyMsg, String keyDetail) {
		FacesContext.getCurrentInstance().addMessage(null, 
				createMessage(FacesMessage.SEVERITY_FATAL, keyMsg, keyDetail));
	}
	
	protected void fatalMessage(String keyMsg, Throwable t) {
		fatalMessage(keyMsg, t, "");
	}
	
	protected void fatalMessage(String keyMsg, Throwable t, Object... params) {
		
		FacesContext.getCurrentInstance().addMessage(null, 
					createMessage(FacesMessage.SEVERITY_FATAL, keyMsg, params));

		t.printStackTrace();
		//TODO: Abilitar Log
		//Logger.getLogger().error(msg, t);
	}
	
	protected HttpServletResponse getResponse() {
		FacesContext context = FacesContext.getCurrentInstance();
		return (HttpServletResponse) context.getExternalContext().getResponse();
	}

	protected void destroy(String name) {
		FacesUtils.destroyBean(name);
	}
	
	protected void responseComplete() {
		FacesContext.getCurrentInstance().responseComplete();
	}
	
	private FacesMessage createMessage(Severity severity, String keyMessage, Object... params) {
		return createMessage(severity, keyMessage, keyMessage, params);
	}
	
	private FacesMessage createMessage(Severity severity, String keyMessage, String detail) {
		return createMessage(severity, keyMessage, true, "");
	}
	
	private FacesMessage createMessage(Severity severity, String keyMessage, String detail, Object... params) {
		return createMessage(severity, keyMessage, detail, true, params);
	}
	
	private FacesMessage createMessage(Severity severity, String keyMessage, boolean isI18n, Object... params) {
		return createMessage(severity, keyMessage, keyMessage, isI18n, params);
	}
	
	private FacesMessage createMessage(Severity severity, String keyMessage, String keyDetail, boolean isI18n, Object... params) {
		
		String message = isI18n ? Messages.getMessage(Messages.DEFAULT_KEY, keyMessage, params) : keyMessage;
		String detail = isI18n ? Messages.getMessage(keyDetail) : keyDetail;
		
		return new FacesMessage(severity, message, detail);
	}
}
