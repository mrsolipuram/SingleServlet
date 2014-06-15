/**
 * 
 */
package com.single.controllers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author madhava
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD) 
public @interface SResponse {

	public enum ResType {
		   JSON, SEND_REDIRECT, FORWARD
		}
	 
	ResType ResType() default ResType.SEND_REDIRECT;
}
