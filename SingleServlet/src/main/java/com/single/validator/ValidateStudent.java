package com.single.validator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.BeanErrors;
import org.apache.commons.validator.GenericValidator;

public class ValidateStudent {

	public void validateStudent(HttpServletRequest req, BeanErrors errors){
		String firstName = req.getParameter("firstName");
		String emailId = req.getParameter("emailId");
		//validating the firstname using commons-validator
		if(GenericValidator.isBlankOrNull(firstName)){
			//adding the error messages to errors 
			errors.addError("firstName", "name.required");
		}
		if(GenericValidator.isBlankOrNull(emailId)){
			errors.addError("emailId", "email.required");
		}
		
	}
}
