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

/**
 * Servlet Filter implementation class FrontFilter
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
		String[] uris = req.getRequestURI().split("/");
		String uri = null;
		if (uris.length > 0) {
			uri = uris[uris.length - 1];
			try {
				BaseController controller = (BaseController) ctxt.getBean(uri);
				if (controller != null)
					controller.execute(req, resp);
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
		ctxt = WebApplicationContextUtils.getWebApplicationContext(fConfig
				.getServletContext());
	}

}
