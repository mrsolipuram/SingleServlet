/**
 * It is front controller for each request.
 * This class is starting point for every request.
 * Each request is handled in this filter and 
 * controlle is navigated to respective Controller class
 * based on request.
 *  
 */
package com.single.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.single.controllers.BaseController;
import com.single.controllers.SWebUtil;
import com.single.properties.SProperties;

/**
 * Servlet Filter implementation class FrontFilter
 * 
 * @author madhava
 * 
 */
public class FrontFilter implements Filter {

	private ApplicationContext ctxt;

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		// get the request uri and the split it with "/"
		// e.g localhost:8080/SingleServlet/Student
		String[] uris = req.getRequestURI().split("/");
		String uri = null;
		if (uris.length > 0) {
			// get the last path from requested uri
			// eg. /student
			uri = uris[uris.length - 1];
			try {
				// getting the bean from spring application context using the
				// last requested path
				// i.e.eg: student
				BaseController controller = (BaseController) ctxt.getBean(uri);
				if (controller != null)
					controller.execute(req, resp);// calling the controller
													// class execute method
				else
					chain.doFilter(request, response);
			} catch (NoSuchBeanDefinitionException e) {
				chain.doFilter(request, response);
			}

		} else
			chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// getting the spring application context object
		ctxt = WebApplicationContextUtils.getWebApplicationContext(fConfig
				.getServletContext());
		// setting the servlet context in SWebUtil class for further use in
		// this framework
		SWebUtil.setServletContext(fConfig.getServletContext());
		// setting the properties file which is loaded the messages.properties
		// file
		// using spring.
		// This property file is further used for getting the the values of key
		// in properties file
		SWebUtil.setProperties((SProperties) ctxt.getBean("sProperties"));
	}

}
