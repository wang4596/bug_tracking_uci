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

public class UpdateUserServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(UpdateUserServlet.class.getName());
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{	
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String role = request.getParameter("roleSelect");
		String emailID = request.getParameter("emailIDHidden");
	    
		try{
		    	//Creating new User in the Databases
		    	log.debug("updating user...");
		    	boolean updateUser = false;
		    	UserBean updatedUserBean = new UserBean();
		    	updatedUserBean.setEmailID(emailID);
		    	updatedUserBean.setFirstName(firstName);
		    	updatedUserBean.setLastName(lastName);
		    	updatedUserBean.setRole(role);
		    	
		    	try{
		    		updateUser = UserDAO.updateUser(updatedUserBean);
		    	}catch(Exception ex){
		    		log.error("Error updating user: "+ ex.getMessage());
		    		ex.printStackTrace();
		    	}
		    	if(updateUser){
		    		log.debug("User updated successfully!");
		    		//Success-Forward to HomPage
		    		HttpSession session = request.getSession(true);
	    		    session.setAttribute("emailID", emailID);
	    		    session.setAttribute("firstName" , firstName);
	    		    session.setAttribute("lastName" , lastName);
	    		    request.setAttribute("UPDATE_MSG" , "User updated successfully!");
	    		    RequestDispatcher dispatcher = getServletConfig().getServletContext().getRequestDispatcher("/Account/AccountUpdateSuccessPage.jsp");
		            dispatcher.forward(request,response);
		    	}else{
		    		//New user not created, alert user with error message!
		    		log.debug("Error occured while updating user in the database.");
		    		request.setAttribute("UPDATE_MSG" , "Error occured while updating user in the database.");
					RequestDispatcher dispatcher = getServletConfig().getServletContext().getRequestDispatcher("/Account/AccountUpdateSuccessPage.jsp");
				    dispatcher.forward(request,response);
		    	}
	    }catch(Exception ex){
	    	log.error("Error updating user: "+ ex.getMessage());
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