package com.single.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControleServlet extends HttpServlet{
	
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String[] uris=req.getRequestURI().split("/");
		String uri=null;
		if(uris.length > 0){
			uri = uris[uris.length-1];
			if(uri.equals("smr")){
			PrintWriter out = resp.getWriter();
			out.write("URL:"+uri);
			System.out.println(uri);
			}
		}
	}

}
