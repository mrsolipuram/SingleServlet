/**
 * It is a Base interface for this framework.
 * Just implement this filter and override the execute method.
 * The framework will automatically calls the your controller classes.
 * 
 */
package com.single.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author madhava
 * 
 */
public interface BaseController {

	/**
	 * Override this method in the implementation class to call this method
	 * If you are implementing this class and override this method then it will automatically call your
	 * implementation execute method.
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException;

}
