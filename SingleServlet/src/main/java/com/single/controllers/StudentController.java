/**
 * 
 */
package com.single.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author madhava
 * 
 */
public class StudentController extends DispatchController {

	public void getStudentJson(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter();
		out.write("{\"name\":\"student\",\"emailId\":\"student@ad.com\",\"age\":\"28\"}");
		out.flush();
	}
	
	public void studentSignUP(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String firstName = req.getParameter("firstName");
		String emailId = req.getParameter("emailId");
		System.out.println(firstName +":stuudent signup:"+emailId);
		resp.sendRedirect(req.getContextPath()+"/welcome.jsp");
	}
}
