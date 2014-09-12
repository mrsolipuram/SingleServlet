/**
 * 
 */
package org.apache.commons.validator;

import java.util.HashMap;
import java.util.Map;

import com.single.properties.SProperties;

/**
 * @author madhava
 *
 */
public class BeanErrors {
	
	private SProperties sProperties;
	private Map<String, Object> errors = new HashMap<String, Object>();
	
	
	public SProperties getsProperties() {
		return sProperties;
	}

	public void setsProperties(SProperties sProperties) {
		this.sProperties = sProperties;
	}

	
	public void addError(String key,String value){
		errors.put(key, sProperties.getProperty(value, value));
	}
	
	public void addMessage(String key,String value){
		errors.put(key, value);
	}
	
	public Map<String, Object> getErrors(){
		return errors;
	}

}
