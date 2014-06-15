/**
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

	public void execute(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException;
}
