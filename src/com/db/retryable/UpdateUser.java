package com.db.retryable;

import com.beans.UserBean;
import com.util.DatabaseConnection;
import com.util.PropFileUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;

public class UpdateUser {
	private static Logger log = Logger.getLogger(UpdateUser.class.getName());
	private UserBean updateUserBean = new UserBean();
	public UpdateUser(UserBean user){
		updateUserBean.setEmailID(user.getEmailID());
		updateUserBean.setFirstName(user.getFirstName());
		updateUserBean.setLastName(user.getLastName());
		updateUserBean.setRole(user.getRole());
	}
	
	public boolean updateAccount() throws SQLException{
		Connection connection = DatabaseConnection.getConnection();
		String sqlQuery = null;
        PreparedStatement statement = null;
        int rowCount = 0;        		

        sqlQuery = "update BugTracker.users set firstname = '"+ updateUserBean.getFirstName()+ "', "
        		+ "lastname= '"+ updateUserBean.getLastName()+"', "
        				+ "role = '"+updateUserBean.getRole()+"' "
        						+ "where emailID='"+updateUserBean.getEmailID() +"' ";
       
        statement = connection.prepareStatement(sqlQuery);
        rowCount = statement.executeUpdate();
        if(rowCount == 1){
        	return true;
        }
        return false;
        
	}
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		PropFileUtil.load();
		UserBean user = new UserBean();
		user.setEmailID("admin@gmail.com");
		user.setFirstName("First");
		user.setLastName("Last");
		user.setRole("Role");
		UpdateUser create = new UpdateUser(user);
		try {
			create.updateAccount();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
		
	}

}
