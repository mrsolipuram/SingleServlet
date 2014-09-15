package com.single.validator;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.BeanErrors;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.single.controllers.SMessage;

public class PerformValidations {

	public void performValidations(HttpServletRequest req, BeanErrors errors,
			String formId, SValidationRules validationRules) {
		JSONObject formRules = validationRules.getFormValidations(formId);
		JSONObject rules = (JSONObject) formRules.get("rules");
		Set<String> fields = rules.keySet();
		for (String formField : fields) {
			String fieldValue = req.getParameter(formField);
			JSONArray fieldRules = (JSONArray) rules.get(formField);
			for (int i = 0; i < fieldRules.size(); i++) {
				JSONObject fiedlRule = (JSONObject) fieldRules.get(i);
				if (!executeValidateMethod(fiedlRule, fieldValue,
						validationRules, formField, errors)) {
					// System.out.println(fieldRule);
					// errors.addError(formField,
					// (String)fiedlRule.get("message"));
					break;
				}
			}
		}
		// old json rules validation
		/*
		 * JSONObject rules = (JSONObject) formRules.get("rules"); JSONObject
		 * messages = (JSONObject) formRules.get("messages"); Set<String>
		 * formFields = rules.keySet(); for (String formField : formFields) {
		 * String fieldValue = req.getParameter(formField); JSONObject field =
		 * (JSONObject) rules.get(formField); JSONObject msg = (JSONObject)
		 * messages.get(formField); Set<String> fieldKeys = field.keySet();
		 * for(String fieldRule:fieldKeys){ System.out.println(fieldRule);
		 * if(!executeValidateMethod(fieldRule, fieldValue, validationRules)){
		 * System.out.println(fieldRule); errors.addError(formField,
		 * (String)msg.get(fieldRule)); break; } } }
		 */

	}

	private boolean executeValidateMethod(JSONObject fiedlRule,
			String fieldValue, SValidationRules validationRules,
			String formField, BeanErrors errors) {
		String methodName = (String) fiedlRule.get("rule");
		String ruleValue = (String)fiedlRule.get("ruleValue");
		boolean result = false;
		@SuppressWarnings("rawtypes")
		Class classReq = validationRules.getBaseValidator().getClass();
		// get the methodName
		try {
			if (methodName != null) {
				Class[] parameters = getMethodParameters(ruleValue); 
				@SuppressWarnings("unchecked")
				Method method = classReq.getMethod(methodName,parameters);
				Object[] args = getMethodArguments(fieldValue, ruleValue, parameters.length);
				result = (Boolean) method.invoke(
						validationRules.getBaseValidator(), args);
				if (!result) {
					addErrorMessage(formField, errors, fiedlRule, method);
				}
			}
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}
	
	private Class[] getMethodParameters(String ruleValue){
		Class[] args = new Class[]{String.class};
		if(ruleValue != null){
			if(!Boolean.parseBoolean(ruleValue)){
				args  = Arrays.copyOf(args, args.length + 1);
				args[args.length - 1] = String.class;
			}
		}
		return args;
	}
	
	private Object[] getMethodArguments(String fieldValue,String ruleValue,int plength){
		Object[] args = new Object[] { fieldValue };
		if(plength == 2){
			args  = Arrays.copyOf(args, args.length + 1);
			args[args.length - 1] = ruleValue;
		}
		return args;
	}

	private void addErrorMessage(String formField, BeanErrors errors,
			JSONObject fiedlRule, Method method) throws Exception {
		String message = (String) fiedlRule.get("message");
		if (message == null) {
			SMessage sMessage = method.getAnnotation(SMessage.class);
			if (sMessage == null)
				throw new Exception("Missed SMessage annotation: for :"
						+ method.getName()+" validate method");
			else {
				if (!sMessage.key().equals("")) {
					message = sMessage.key();
					errors.addError(formField, message);
				} else if(!sMessage.message().equals("")){
					message = sMessage.message();
					errors.addMessage(formField, message);
				}
			}
		} else {
			errors.addError(formField, message);
		}
		if (message == null){
			errors.addMessage(formField, "Message is not defined");
			throw new Exception("Message is not defined for:" + formField
					+ " :define annotation or add message in rules");
		}
	}

}
