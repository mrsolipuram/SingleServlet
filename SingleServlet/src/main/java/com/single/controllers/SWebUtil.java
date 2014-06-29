/**
 * 
 */
package com.single.controllers;

import javax.servlet.ServletContext;

import com.single.properties.SProperties;

/**
 * @author madhava
 *
 */
public class SWebUtil {
	private static ServletContext servletContext;
	private static SProperties properties;
	
	public static ServletContext getServletContext(){
		return servletContext;
	}
	
	public static void setServletContext(ServletContext servletContext){
		SWebUtil.servletContext = servletContext;
	}

	public static SProperties getProperties() {
		return properties;
	}

	public static void setProperties(SProperties properties) {
		SWebUtil.properties = properties;
	}
	

}
