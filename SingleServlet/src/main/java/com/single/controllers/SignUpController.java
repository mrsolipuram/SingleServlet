/**
 * 
 */
package com.single.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author madhava
 *
 */
public class SignUpController implements BaseController{
	
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String firstName = req.getParameter("firstName");
		String emailId = req.getParameter("emailId");
		System.out.println(firstName +":::"+emailId);
		resp.sendRedirect(req.getContextPath()+"/welcome.jsp");
		
	}

}
