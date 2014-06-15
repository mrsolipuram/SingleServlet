/**
 * 
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
				// call the method
				method.invoke(this, args);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
