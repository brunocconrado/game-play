/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gp.inventory.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.com.embracon.j4e.i18n.Messages;
import br.com.embracon.j4e.logging.ConfigurableLoggingService;
import br.com.embracon.j4e.logging.JavaLoggingStrategy;
import br.com.embracon.j4e.logging.Logger;
import br.com.embracon.j4e.logging.LoggingService;
import br.com.embracon.j4e.services.ServiceRegistry;
import br.com.embracon.j4e.thread.ThreadLoggingFactory;
import br.com.gp.inventory.domain.application.Application;
import br.com.gp.inventory.domain.application.exception.ApplicationException;
import br.com.gp.inventory.utils.TeamPositionProperties;

/**
 * Web application lifecycle listener.
 * @author bruno.conrado
 */
public class TeamPositionListener implements ServletContextListener {

	ServletContext ctx;

	@Override
	public void contextInitialized(ServletContextEvent event) {

		if (event != null) {
			ctx = event.getServletContext();
			Application.contentPath = ctx.getRealPath("/");

			if(Application.contentPath.contains("target")) {
				Application.contentPath  = ctx.getRealPath("WEB-INF/main/webapp/");
			}

			if(!Application.contentPath.endsWith("/") || !Application.contentPath.endsWith("\\") ) {
				Application.contentPath += "/";
			}
		}

		try {

			//Set default Messsage bundle
			Messages.setDefaultBundle("messages");

			this.loggingFactory();

			Logger.getLogger().info("starting Aplication......");

			Application.config();            

			Logger.getLogger().info("Location is:" + ctx.getRealPath("/"));
			Logger.getLogger().info("Log can be found at " + Messages.getMessage("system.loggingPath"));
			Logger.getLogger().info("Archive started");

		} catch (Exception e) {
			Logger.getLogger().fatal("Error to trying system initialize.", e);
			throw new ApplicationException(e);
		}
	}

	private void loggingFactory() throws InterruptedException {

		//Registra o logging no service.
		ConfigurableLoggingService service = new ConfigurableLoggingService(
				new JavaLoggingStrategy(TeamPositionProperties.LOGGING_SYSTEM));
		ServiceRegistry.register(LoggingService.class, service);

		Boolean isStartLogging = Boolean.parseBoolean(Messages.getMessage("system.loggingThread").trim());
		if (isStartLogging) {

			ThreadLoggingFactory logging = ThreadLoggingFactory.getInstance(
					Boolean.parseBoolean(Messages.getMessage("system.loggingThread.first").trim()),
					Boolean.parseBoolean(Messages.getMessage("system.logging.use.same.on.restart").trim()),
					Messages.getMessage(TeamPositionProperties.LOGGING_SYSTEM_PROPERTIE));

			//Add list of logging
			logging.addLoggingName(TeamPositionProperties.LOGGING_SYSTEM);
			// logging.addLoggingName(TeamPositionProperties.FTP_REMOVE_LOG);

			logging.start();

			Thread.sleep(2000L);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		ctx = null;
		Logger.getLogger().closeAll();

	}
}
