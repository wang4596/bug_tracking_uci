package com.db.dao;

import com.beans.UserBean;
import com.db.retryable.CreateUser;
import com.db.retryable.GetUser;
import com.db.retryable.IsExistingUser;
import com.db.retryable.LoginUser;
import com.db.retryable.ResetUserPassword;

public class UserDAO {

	public static boolean isExistingUser(String emailID) throws Exception{
		IsExistingUser existingUser = new IsExistingUser(emailID);
		return existingUser.isExistingUser();
	}
	
	public static boolean createUser(UserBean user) throws Exception{	
		CreateUser newUser = new CreateUser(user);
		return newUser.registerUser();		
	}
	
	public static UserBean loginUser(String emailID, String password) throws Exception{
		LoginUser loginChecker = new LoginUser(emailID, password);
		UserBean loggedInUser = null;
		loggedInUser = loginChecker.loginUser();
		return loggedInUser;
	}
	
	public static UserBean getUser(String emailID) throws Exception{
		UserBean existingUser = new UserBean();
		GetUser readUser = new GetUser(emailID);
		existingUser = readUser.getUser();
		return existingUser;
	}
	
	public static boolean updateUserPassword(UserBean user) throws Exception{	
		ResetUserPassword resetUserPass = new ResetUserPassword(user);
		return resetUserPass.resetPassword();		
	}
}