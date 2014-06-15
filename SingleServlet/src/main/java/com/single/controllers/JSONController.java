package com.single.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author madhava
 *
 */
public class JSONController implements BaseController{

	public void execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		out.write("{\"name\":\"ABC\",\"emailId\":\"ddd@ad.com\",\"age\":\"28\"}");;
		out.flush();
		
	}

}
