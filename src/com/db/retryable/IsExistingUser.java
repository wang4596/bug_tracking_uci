package com.db.retryable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.util.DatabaseConnection;

public class IsExistingUser {
	private static Logger log = Logger.getLogger(IsExistingUser.class.getName());
	private String emailID = null;
	public IsExistingUser(String emailID){
		this.emailID = emailID;
	}

	public boolean isExistingUser() throws Exception{
		log.debug("Entering method isExistingUser inside file IsExistingUser");
		boolean existingUser = false;
		Connection connection = DatabaseConnection.getConnection();
		try {
			String sqlQuery = null;
	        PreparedStatement statement = null;
	        int rowCount = 0;        		

	        sqlQuery = "Select * from BugTracker.Users where emailID='"+this.emailID+"'";
	        statement = connection.prepareStatement(sqlQuery);
			ResultSet results = statement.executeQuery();
			if (results != null) {
				while (results.next()) {
					if(this.emailID.equals(results.getString("emailID"))){
						existingUser = true;
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
		}
		log.debug("Exiting method isExistingUser inside file IsExistingUser");
		return existingUser;        
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
