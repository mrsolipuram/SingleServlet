package com.single.validator;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class SValidationRules {

	private String fileName;
	private SBaseValidator baseValidator;
	private JSONObject jsonObject;
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public SBaseValidator getBaseValidator() {
		return baseValidator;
	}

	public void setBaseValidator(SBaseValidator baseValidator) {
		this.baseValidator = baseValidator;
	}

	public void init() {
		try {

			FileReader reader = new FileReader(Thread.currentThread().getContextClassLoader()
					.getResource(fileName).getPath());
			// Thread.currentThread().getContextClassLoader().getResource("student.json").getPath());
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(reader);
			jsonObject = (JSONObject) obj;
			System.out.println("Validation rules loaded");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public JSONObject getFormValidations(String formId){
		JSONObject formValidator = null;		
		formValidator = (JSONObject) jsonObject.get(formId);		
		return formValidator;
	}
}