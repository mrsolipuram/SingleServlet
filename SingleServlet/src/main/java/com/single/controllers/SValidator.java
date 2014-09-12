/**
 * This annotaion is used to specify the validation class name and methodname to call the validations before calling 
 * controller method. It will send the response based on SResponse annotaiton type.
 */
package com.single.controllers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author madhava
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SValidator {

	/**
	 * validation fully qualified class name, it is not mandatory it this value
	 * is not specified it will treat as same class otherwise it will loads and
	 * create the object of specified validation class
	 * 
	 * @return
	 */
	String CLASS_NAME() default "SameClass";

	/**
	 * it is a mandatory field. the validation method name can be anything it
	 * will not receive any return type. but the parameter must be in this order
	 * (HttpServletRequest.class,BeanErrors.class )
	 * 
	 * @return
	 */
	String METHOD_NAME() default "";
	
	String FORM_ID() default "";

}
