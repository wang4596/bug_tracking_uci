package com.beans;

public class UserBean {

	private String firstName;
	private String lastName;
	private String emailID;
	private String password;
	private String role;
	
	public void setFirstName(String firstName){
		this.firstName = firstName;
		
	}
	public String getFirstName(){
		return this.firstName;
	}
	
	public void setLastName(String lastName){
		this.lastName = lastName;
		
	}
	public String getLastName(){
		return this.lastName;
	}
	
	public void setEmailID(String emailID){
		this.emailID = emailID;
		
	}
	public String getEmailID(){
		return this.emailID;
	}
	
	public void setPassword(String password){
		this.password = password;
		
	}
	public String getPassword(){
		return this.password;
	}
	
	public void setRole(String role){
		this.role = role;
		
	}
	public String getRole(){
		return this.role;
	}
}
