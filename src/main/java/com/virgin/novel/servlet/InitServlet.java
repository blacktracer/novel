package com.virgin.novel.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 9121954278303488600L;
	private static Logger logger = Logger.getLogger(InitServlet.class);
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String contextPathValue = config.getInitParameter("contextPath");
		logger.debug(contextPathValue);
		ServletContext servletContext = config.getServletContext();
		servletContext.setAttribute("contextPath",contextPathValue);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("InitServlet invoked");
		PrintWriter writer = response.getWriter();
		response.setContentType("text/html;charset=utf-8");
		String contextPath = (String) getServletContext().getAttribute("contextPath");
		writer.print("<h>"+contextPath+"</h>");
		writer.flush();
		writer.close();
	}
}
