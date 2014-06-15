/**
 * 
 */
package com.single.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.single.beans.Student;
import com.single.controllers.SResponse.ResType;

/**
 * @author madhava
 *
 */
public class StudentDynaResponse extends DynaResponseController{

	@SResponse(ResType=ResType.SEND_REDIRECT)
	public String studentRedirect(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String firstName = req.getParameter("firstName");
		String emailId = req.getParameter("emailId");
		System.out.println(firstName +":stuudent dyna signup:"+emailId);
		return "/welcome.jsp";
	}
	
	@SResponse(ResType=ResType.FORWARD)
	public String studentFroward(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String firstName = req.getParameter("firstName");
		String emailId = req.getParameter("emailId");
		System.out.println(firstName +":stuudent dyna signup:"+emailId);
		return "/welcome.jsp";
	}
	
	@SResponse(ResType=ResType.JSON)
	public Student studentJSON(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		
		Student s = new Student();
		s.setEmailId("smrdynares@ad.com");
		s.setName("dynares");
		return s;
	}
}
