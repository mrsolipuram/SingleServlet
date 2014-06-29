/**
 * It is a framework class. It is implemented the BaseController and overridden the
 * execute method in execute method it will call the target controller class custome 
 * method based on request parameter.
 * 
 * When user want to write single controller and handle all the requests
 * just extend this class and write your own methods with request,resonse 
 * as parameters
 * 
 * Use should handle the response handling code like sending json data, forwarding the request
 * sendredirecting to another url. 
 */
package com.single.controllers;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author madhava
 * 
 */
public class DispatchController implements BaseController {

	public final void execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		@SuppressWarnings("rawtypes")
		Class classReq = this.getClass();
		@SuppressWarnings("rawtypes")
		Class[] types = new Class[] { HttpServletRequest.class,
				HttpServletResponse.class };
		// get the methodName
		String methodName = req.getParameter("parameter");
		try {
			if (methodName != null) {
				@SuppressWarnings("unchecked")
				Method method = classReq.getMethod(methodName, types);
				// create the object array
				Object[] args = new Object[] { req, resp };
				// call the controller method
				method.invoke(this, args);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
