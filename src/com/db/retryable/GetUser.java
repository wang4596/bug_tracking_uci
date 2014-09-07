package com.db.retryable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import com.beans.UserBean;
import com.util.DatabaseConnection;

public class GetUser {
	private static Logger log = Logger.getLogger(GetUser.class.getName());
	private String emailID = null;
	UserBean user = new UserBean();
	public GetUser(String emailID){
		this.emailID = emailID;
	}

	public UserBean getUser() throws Exception{
		log.debug("Entering method getUser() inside file GetUser");
		Connection connection = null;
		PreparedStatement statement = null;        
		try {
			connection = DatabaseConnection.getConnection();
			String sqlQuery = "Select * from BugTracker.Users where emailID='"+this.emailID+"'";
	        statement = connection.prepareStatement(sqlQuery);
			ResultSet results = statement.executeQuery();
			if (results != null) {
				while (results.next()) {
					if(results.getString("emailID") != null){
						user.setEmailID(results.getString("emailID"));
					}
					if(results.getString("firstname") != null){
						user.setFirstName(results.getString("firstname"));
					}
					if(results.getString("lastname") != null){
						user.setLastName(results.getString("lastname"));
					}
					if(results.getString("role") != null){
						user.setRole(results.getString("role"));
					}
				}
			}   
		} catch (Exception e) {
			log.debug("Error occured while checking for existing user: "+ e.getMessage());
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}finally{
			if(connection!=null){
				connection.close();
			}
			if(statement!=null){
				statement.close();
			}
		}
		log.debug("Exiting method getUser() inside file GetUser");
		return user;        
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}