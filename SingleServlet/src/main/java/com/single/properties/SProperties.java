/**
 * 
 */
package com.single.properties;

import java.io.IOException;
import java.util.Properties;

/**
 * @author madhava
 *
 */
public class SProperties {
	
	private Properties properties;
	
	public SProperties(String filePath) {
		System.out.println(filePath);
		try{
		properties = new Properties();
		properties.load(Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(filePath));
		System.out.println(properties.size());
		}catch(IOException e){
			e.printStackTrace();
		}
		System.out.println("properties loaded successfully");
	}
	
	public String getProperty(String key){
		if(properties != null){
			return properties.getProperty(key);
		}else
			return null;
		
	}
	
	public String getProperty(String key,String defaultValue){
		if(properties != null){
			return properties.getProperty(key,defaultValue);
		}else
			return null;
	}

}
