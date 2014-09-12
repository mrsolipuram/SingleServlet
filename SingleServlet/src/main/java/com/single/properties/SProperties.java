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
		try {
			properties = new Properties();
			properties.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(filePath));
			System.out.println(properties.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("properties loaded successfully");
	}

	public String getProperty(String key) {
		String value = null;
		try {
			if (properties != null) {
				value = properties.getProperty(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;

	}

	public String getProperty(String key, String defaultValue) {
		String value = null;
		try {
			if (properties != null) {
				value = properties.getProperty(key, defaultValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

}
