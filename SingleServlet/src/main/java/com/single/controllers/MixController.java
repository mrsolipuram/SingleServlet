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
public class MixController extends DispatchController{

	public void getJson(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		PrintWriter out = resp.getWriter();
		out.write("{\"name\":\"ABCq\",\"emailId\":\"dddv@ad.com\",\"age\":\"29\"}");;
		out.flush();
	}
}
