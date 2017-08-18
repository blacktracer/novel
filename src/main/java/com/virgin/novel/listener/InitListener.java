package com.virgin.novel.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

public class InitListener implements ServletContextListener {
	private static Logger logger = Logger.getLogger(InitListener.class);
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String log4jConfigLocation = sce.getServletContext().getInitParameter("log4jConfigLocation");
		logger.debug(log4jConfigLocation);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
}
