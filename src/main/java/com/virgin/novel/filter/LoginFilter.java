package com.virgin.novel.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class LoginFilter implements Filter {
	private static Logger logger = Logger.getLogger(LoginFilter.class);
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String name = filterConfig.getInitParameter("name");
		ServletContext servletContext = filterConfig.getServletContext();
		servletContext.setAttribute("name",name);
		logger.debug(name);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		String name = (String) request.getServletContext().getAttribute("name");
		if(!"迪丽热巴".equals(name)){
			logger.debug("doFilter invoked");
			chain.doFilter(request, response);
		}else{
			HttpServletRequest req = (HttpServletRequest) request;
			logger.debug(req.getContextPath());
			//HttpServletResponse resp = (HttpServletResponse) response;
			//resp.sendRedirect(req.getContextPath()+"/index.jsp");
			req.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	@Override
	public void destroy() {
		logger.debug("destroy");
	}
}
