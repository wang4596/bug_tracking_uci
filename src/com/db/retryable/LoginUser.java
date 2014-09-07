package com.db.retryable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import com.beans.UserBean;
import com.servlet.LoginServlet;
import com.util.DatabaseConnection;

public class LoginUser {
	private String emailID = null;
	private String password = null;
	UserBean loggedInUser = null;
	private static Logger log = Logger.getLogger(LoginUser.class.getName());
	
	public LoginUser(String emailID, String password){
		this.emailID = emailID;
		this.password = password;
	}

	public UserBean loginUser() throws Exception{
		Connection connection = DatabaseConnection.getConnection();
		try {
			log.debug("Entering method loginUser() in class LoginUser.java...");
			String sqlQuery = null;
	        PreparedStatement statement = null;

	        sqlQuery = "Select * from BugTracker.Users where emailID='"+this.emailID+"' and password='" + this.password+ "'";
	        statement = connection.prepareStatement(sqlQuery);
			ResultSet results = statement.executeQuery();
			
			if (results != null) {
				while (results.next()) {
					if(emailID.equalsIgnoreCase(results.getString("emailID"))){
						loggedInUser = new UserBean();
						if(results.getString("emailID") != null){
							loggedInUser.setEmailID(results.getString("emailID"));
						}
						if(results.getString("firstname") != null){
							loggedInUser.setFirstName(results.getString("firstname"));
						}
						if(results.getString("lastname") != null){
							loggedInUser.setLastName(results.getString("lastname"));
						}
						if(results.getString("role") != null){
							loggedInUser.setLastName(results.getString("role"));
						}
					}else{
						log.debug("EmailID or password doesnot match the database, please try again.");
						throw new Exception("EmailID or password doesnot match the database, please try again.");
					}
				}
			}
		} catch (Exception e) {
			log.debug("Error occured while retrieving users login data: "+ e.getMessage());
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}finally{
			if(connection!=null){
				connection.close();
			}
		}
		log.debug("Exiting method loginUser() in class LoginUser.java...");
		return loggedInUser;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
