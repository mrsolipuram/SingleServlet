/**
 * 
 */
package com.single.beans;

import java.io.Serializable;

/**
 * @author madhava
 *
 */
public class Student implements Serializable{
	
	private String name;
	private String emailId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	

}
