package com.single.validator;

import org.apache.commons.validator.GenericValidator;

import com.single.controllers.SMessage;

public class SBaseValidator {
	
	@SMessage(message="This field required")
	public boolean required(String value){
		return !GenericValidator.isBlankOrNull(value);
	}
	
	public boolean integer(String value){
		return GenericValidator.isInt(value);
	}
	
	public boolean floatNumber(String value){
		return GenericValidator.isFloat(value);
	}
	
	@SMessage()
	public boolean email(String value){
		return GenericValidator.isEmail(value);
	}
	
	public boolean minLength(String value, String min){
		return GenericValidator.minLength(value, Integer.parseInt(min));
	}
	
	public  boolean maxLength(String value, String max){
		return GenericValidator.maxLength(value, Integer.parseInt(max));
	}
	
	public boolean minValue(String value, String min){
		return GenericValidator.minValue(Integer.parseInt(value), Integer.parseInt(min));
	}
	
	public boolean url(String value){
		return GenericValidator.isUrl(value);
	}
	
	public boolean date(String value,String datePattern){
		return GenericValidator.isDate(value, datePattern, true);
	}
	
	public boolean regexExp(String value,String pattern){
		return GenericValidator.matchRegexp(value, pattern);
	}
}
