package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.beans.UserBean;
import com.db.dao.UserDAO;
import com.util.PasswordEncrypt;
import com.util.PropFileUtil;
import com.util.SendMailTLS;

public class ForgetPassServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ForgetPassServlet.class.getName());
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{	
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		String emailID = request.getParameter("emailID");
		boolean isExistingUser = false;
		try{
			log.debug("Checking if the email address exists in the Database");
			isExistingUser = UserDAO.isExistingUser(emailID);
		}catch(Exception ex){
			ex.printStackTrace();
			log.debug("Error occured while checking for user in DB: "+ ex.getMessage());
			request.setAttribute("ERROR_MSG" , "Error occured while checking for user in DB: "+ ex.getMessage());
			RequestDispatcher dispatcher = getServletConfig().getServletContext().getRequestDispatcher("/PasswordResetErrorPage.jsp");
			dispatcher.forward(request,response);
		}
		if(isExistingUser){
			log.debug("Reseting the password for existing user.");
			boolean passwordResetSuccess = false;
			String temp_Password = "bugtracker123";
			UserBean resetUserPass = new UserBean();
			resetUserPass.setEmailID(emailID);
			resetUserPass.setPassword(PasswordEncrypt.cryptWithMD5(temp_Password));
			//Update Database with temp password
			try {
				passwordResetSuccess = UserDAO.updateUserPassword(resetUserPass);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("Error occured while resetting user password in DB: "+ e.getMessage());
				request.setAttribute("ERROR_MSG" , "Error occured while resetting user password in DB: "+ e.getMessage());
				RequestDispatcher dispatcher = getServletConfig().getServletContext().getRequestDispatcher("/PasswordResetErrorPage.jsp");
				dispatcher.forward(request,response);
			}
			//Send Email
			if(passwordResetSuccess){
				StringBuilder emailBody = new StringBuilder("");
				emailBody.append("Hello,");
				emailBody.append("\n");
				emailBody.append("We have reset your account password to: "+ temp_Password + ", please try logging into the system.");
				SendMailTLS.SendEmail(PropFileUtil.getValue("MAIL_USERNAME"), emailID, "Bugtracker Password Reset Nofitication.", emailBody.toString());
				
				log.debug("Password for email address "+emailID+" has been successfully reset, please check your email for your new password.");
				request.setAttribute("SUCCESS_MSG" , "Password for email address "+emailID+" has been successfully reset, please check your email for your new password.");
				RequestDispatcher dispatcher = getServletConfig().getServletContext().getRequestDispatcher("/PasswordResetSuccessPage.jsp");
				dispatcher.forward(request,response);
			}else{
				log.error("Error occured while reseting password, please try again.");
				request.setAttribute("ERROR_MSG" , "Error occured while reseting password, please try again.");
				RequestDispatcher dispatcher = getServletConfig().getServletContext().getRequestDispatcher("/PasswordResetErrorPage.jsp");
				dispatcher.forward(request,response);
			}
		}else{
			//Please enter a valid emailAddress.
			log.error("EmailID not recognized, please enter valid email Address.");
	        request.setAttribute("ERROR_MSG" , "EmailID not recognized, please enter valid email Address.");
			RequestDispatcher dispatcher = getServletConfig().getServletContext().getRequestDispatcher("/LoginPage.jsp");
	        dispatcher.forward(request,response);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
