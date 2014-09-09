package com.servlet.Account;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.beans.UserBean;
import com.db.dao.UserDAO;
import com.util.PasswordEncrypt;

public class UpdateUserPasswordServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(UpdateUserPasswordServlet.class.getName());
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{	
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		String emailID = request.getParameter("emailID");
		String oldPassword = PasswordEncrypt.cryptWithMD5(request.getParameter("oldPassword"));
		
		UserBean loggedInUser = null;
		log.debug("Logging in user...");
	    try {
			loggedInUser = UserDAO.loginUser(emailID, oldPassword);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("error occured while logging in: "+ e.getMessage());
		}
		
	    if(loggedInUser!=null){
	    	try{
		    	//Updating User password in the Databases
		    	log.debug("updating user password...");
		    	boolean updateUser = false;
		    	UserBean updatedPasswordBean = new UserBean();
		    	updatedPasswordBean.setEmailID(emailID);
		    	updatedPasswordBean.setPassword(PasswordEncrypt.cryptWithMD5(request.getParameter("newPassword2")));
		    	
		    	try{
		    		updateUser = UserDAO.updateUserPassword(updatedPasswordBean);
		    	}catch(Exception ex){
		    		log.error("Error updating user password: "+ ex.getMessage());
		    		ex.printStackTrace();
		    	}
		    	if(updateUser){
		    		log.debug("User password updated successfully!");
		    		//Success-Forward
	    		    request.setAttribute("UPDATE_MSG" , "User password updated successfully!");
	    		    RequestDispatcher dispatcher = getServletConfig().getServletContext().getRequestDispatcher("/Account/AccountUpdateSuccessPage.jsp");
		            dispatcher.forward(request,response);
		    	}else{
		    		//password not updated, alert user with error message!
		    		log.debug("Error occured while updating user password in the database.");
		    		request.setAttribute("UPDATE_MSG" , "Error occured while updating user password in the database.");
					RequestDispatcher dispatcher = getServletConfig().getServletContext().getRequestDispatcher("/Account/AccountUpdateSuccessPage.jsp");
				    dispatcher.forward(request,response);
		    	}
	    }catch(Exception ex){
	    	log.error("Error updating user: "+ ex.getMessage());
	    	ex.printStackTrace();
	    }   
	    }else{
	    	log.debug("Current password doesnot match in the database.");
    		request.setAttribute("UPDATE_MSG" , "Error: Current password doesnot match in the database, password was not updated!");
			RequestDispatcher dispatcher = getServletConfig().getServletContext().getRequestDispatcher("/Account/AccountUpdateSuccessPage.jsp");
	    }
		     
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}