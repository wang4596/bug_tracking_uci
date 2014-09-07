package com.servlet;

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

public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(LoginServlet.class.getName());
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{	
		//123
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		String emailID = request.getParameter("emailIDLogin");
	    String password = null;
		if(request.getParameter("password")!= null){
			password = PasswordEncrypt.cryptWithMD5(request.getParameter("password"));	
		}
	    UserBean loggedInUser = null;
		log.debug("Logging in user...");
	    try {
			loggedInUser = UserDAO.loginUser(emailID, password);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("error occured while logging in: "+ e.getMessage());
		}
		if(loggedInUser != null){
			
			//Forward to JSP
			HttpSession session = request.getSession(true);
		    // Set some attribute values to the session
		    // In this case user and password from the request and client
		    session.setAttribute("emailID", loggedInUser.getEmailID());
		    session.setAttribute("firstName", loggedInUser.getFirstName());
		    session.setAttribute("lastName", loggedInUser.getLastName());
	        RequestDispatcher dispatcher = getServletConfig().getServletContext().getRequestDispatcher("/HomePage.jsp");
	        dispatcher.forward(request,response);	
		}else{
			log.error("Login failed, please try again.");
	        request.setAttribute("ERROR_MSG" , "Login failed, please try again.");
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
