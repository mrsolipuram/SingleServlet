/**
 * 
 */
package com.single.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.single.beans.Student;
import com.single.controllers.SResponse.ResType;

/**
 * @author madhava
 * 
 */
public class DynaResponseController implements BaseController {

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
				Object result = method.invoke(this, args);
				if (method
						.isAnnotationPresent(com.single.controllers.SResponse.class)) {
					SResponse a = method.getAnnotation(SResponse.class);
					if (a.ResType().equals(ResType.SEND_REDIRECT)) {
						sendRedirect(req, resp, result.toString());
					} else if (a.ResType().equals(ResType.FORWARD)) {
						sendForward(req, resp, result.toString());
					} else if(a.ResType().equals(ResType.JSON))
						sendJSON(req, resp, result);
					else
						System.out.println("no return type");

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public final void sendRedirect(HttpServletRequest req,
			HttpServletResponse resp, String result) throws IOException {
		resp.sendRedirect(req.getContextPath() + result);
	}

	public final void sendForward(HttpServletRequest req,
			HttpServletResponse resp, String result) throws IOException,
			ServletException {
		RequestDispatcher rd = req.getRequestDispatcher(result);
		rd.forward(req, resp);
	}
	
	public final void sendJSON(HttpServletRequest req,
			HttpServletResponse resp, Object result) throws IOException{
		Gson gson = new Gson();
		String json =gson.toJson(result);	
		PrintWriter out = resp.getWriter();
		out.write(json);
		out.flush();
		out.close();
		
	}

}
