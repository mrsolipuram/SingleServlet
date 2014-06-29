/**
 * It is an example of DynaResponseController.
 * It is similar to StudentController
 * 
 * The DynaResponseController will handle the response code
 * like sending json,sendredirect,forwarding the request.
 * 
 * The DynaResponseController will also handle the validations
 * means it will call the validation method before calling 
 * the controller method.
 * 
 */
package com.single.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.BeanErrors;
import org.apache.commons.validator.GenericValidator;

import com.single.beans.Student;
import com.single.controllers.SResponse.ResType;

/**
 * @author madhava
 * 
 */
public class StudentDynaResponse extends DynaResponseController {

	/**
	 * This is an example for sendredirect and customevalidation in someother
	 * class
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 */
	@SResponse(ResType = ResType.SEND_REDIRECT)
	@SValidator(CLASS_NAME = "com.single.validator.ValidateStudent", METHOD_NAME = "validateStudent")
	public String studentRedirect(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		String firstName = req.getParameter("firstName");
		String emailId = req.getParameter("emailId");
		System.out.println(firstName + ":stuudent dyna signup:" + emailId);
		return "/welcome.jsp";
	}

	/**
	 * It is an example for forward requst with validation within the same class
	 * it should return the pathe for forwarding the request
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 */
	@SResponse(ResType = ResType.FORWARD)
	@SValidator(METHOD_NAME = "validateStudent")
	public String studentFroward(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		String firstName = req.getParameter("firstName");
		String emailId = req.getParameter("emailId");
		System.out.println(firstName + ":stuudent dyna signup:" + emailId);
		return "/welcome.jsp";
	}

	/**
	 * This is and example for sending the json using annotaion method should
	 * return any pojo
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 */
	@SResponse(ResType = ResType.JSON)
	public Student studentJSON(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		Student s = new Student();
		s.setEmailId("smrdynares@ad.com");
		s.setName("dynares");
		return s;
	}

	/**
	 * validation method in the same class. The validation method name can be
	 * anything but the parameters must be reqeust, and beanerrors.
	 * 
	 * @param req
	 * @param errors
	 */
	public void validateStudent(HttpServletRequest req, BeanErrors errors) {
		String firstName = req.getParameter("firstName");
		String emailId = req.getParameter("emailId");
		// validating the firstname using commons-validator
		if (GenericValidator.isBlankOrNull(firstName)) {
			// adding the error messages to errors
			errors.addError("firstName", "name.required");
		}
		if (GenericValidator.isBlankOrNull(emailId)) {
			errors.addError("emailId", "email.required");
		}

	}
}
