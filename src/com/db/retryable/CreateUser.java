package com.db.retryable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import org.apache.log4j.Logger;
import com.beans.UserBean;
import com.util.DatabaseConnection;

public class CreateUser {
	private UserBean newUser = new UserBean();
	private static Logger log = Logger.getLogger(CreateUser.class.getName());
	
	public CreateUser(UserBean user){	
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setEmailID(user.getEmailID());
		newUser.setPassword(user.getPassword());
		newUser.setRole(user.getRole());
	}
	
	public boolean registerUser() throws SQLException{
		log.debug("Entering method registerUser() inside CreateUser");
		Connection connection = DatabaseConnection.getConnection();
		String sqlQuery = null;
        PreparedStatement statement = null;
        int rowCount = 0;        		

        sqlQuery = "INSERT INTO BugTracker.Users(firstname,lastname, emailID, password, role, Created_Date, Updated_Date) " +
        		"VALUES(?,?,?,?,?,?,?)";
       
        statement = connection.prepareStatement(sqlQuery);
        statement.setString(1, newUser.getFirstName());
        statement.setString(2, newUser.getLastName());
        statement.setString(3, newUser.getEmailID());
        statement.setString(4, newUser.getPassword());
        statement.setString(5, newUser.getRole());
        statement.setTimestamp(6, new Timestamp(new Date().getTime()));
        statement.setTimestamp(7, new Timestamp(new Date().getTime()));
        rowCount = statement.executeUpdate();
        if(rowCount == 1){
        	log.debug("Exiting method registerUser() inside CreateUser");
        	return true;
        }
        log.debug("Exiting method registerUser() inside CreateUser");
        return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserBean user = new UserBean();
		user.setFirstName("Test");
		user.setLastName("Test");
		user.setEmailID("Test@gmail.com");
		user.setPassword("Test123");
		CreateUser create = new CreateUser(user);
		try {
			create.registerUser();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}	
	}
}