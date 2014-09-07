package com.db.retryable;

import com.beans.UserBean;

import com.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;

public class ResetUserPassword {
	private static Logger log = Logger.getLogger(ResetUserPassword.class.getName());
	private UserBean newUser = new UserBean();
	public ResetUserPassword(UserBean user){
		newUser.setEmailID(user.getEmailID());
		newUser.setPassword(user.getPassword());
	}
	
	public boolean resetPassword() throws SQLException{
		Connection connection = DatabaseConnection.getConnection();
		String sqlQuery = null;
        PreparedStatement statement = null;
        int rowCount = 0;        		

        sqlQuery = "update BugTracker.users set password = '"+ newUser.getPassword()+ "' where emailID='"+newUser.getEmailID() +"' ";
       
        statement = connection.prepareStatement(sqlQuery);
        rowCount = statement.executeUpdate();
        if(rowCount == 1){
        	return true;
        }
        return false;
        
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserBean user = new UserBean();
		user.setEmailID("admin@gmail.com");
		user.setPassword("Test123");
		ResetUserPassword create = new ResetUserPassword(user);
		try {
			create.resetPassword();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
		
	}

}
