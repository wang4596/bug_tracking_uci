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

public class RegisterUserServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(RegisterUserServlet.class.getName());
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{	
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String emailID = request.getParameter("emailID");
		String role = request.getParameter("roleSelect");
		
		String password = null;
		if(request.getParameter("newPassword")!= null && !request.getParameter("newPassword").equals("")){
			password = PasswordEncrypt.cryptWithMD5(request.getParameter("newPassword"));	
		}
	    
		boolean isExistingUser = false;
		try{
			log.debug("Checking if user already exists in the database...");
	    	isExistingUser = UserDAO.isExistingUser(emailID);
	    	if(isExistingUser){
	    		log.debug("User with emaildID: "+ emailID + ", already exists in database, please signin.");
		    	//Ask user to sign in
				request.setAttribute("ERROR_MSG" , "User already exists, please sign in.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/LoginPage.jsp");
			    dispatcher.forward(request,response);
		    }else{
		    	//Creating new User in the Databases
		    	log.debug("Creating new user...");
		    	boolean userCreated = false;
		    	UserBean newUser = new UserBean();
		    	newUser.setFirstName(firstName);
		    	newUser.setLastName(lastName);
		    	newUser.setRole(role);
		    	newUser.setEmailID(emailID);
		    	newUser.setPassword(password);
		    	try{
		    		userCreated = UserDAO.createUser(newUser);
		    	}catch(Exception ex){
		    		log.error("Error creating new user: "+ ex.getMessage());
		    		ex.printStackTrace();
		    	}
		    	if(userCreated){
		    		log.debug("User created successfully.");
		    		//Success-Forward to HomPage
		    		HttpSession session = request.getSession(true);
	    		    session.setAttribute("emailID", emailID);
	    		    session.setAttribute("firstName" , firstName);
	    		    session.setAttribute("lastName" , lastName);
	    		    RequestDispatcher dispatcher = getServletConfig().getServletContext().getRequestDispatcher("/HomePage.jsp");
		            dispatcher.forward(request,response);
		    	}else{
		    		//New user not created, alert user with error message!
		    		log.debug("New user not created in database.");
		    		request.setAttribute("ERROR_MSG" , "User not created.");
					RequestDispatcher dispatcher = getServletConfig().getServletContext().getRequestDispatcher("/LoginPage.jsp");
				    dispatcher.forward(request,response);
		    	}
		    }
	    }catch(Exception ex){
	    	log.error("Error creating new user: "+ ex.getMessage());
	    	ex.printStackTrace();
	    }        
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}