package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.TicketBean;
import com.db.dao.TicketDAO;


public class createTicketServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{	
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
	    String firstname = (String) session.getAttribute("firstName");
	    String lastname = (String) session.getAttribute("lastname");
	    String project = request.getParameter("project");
		String status = request.getParameter("status");
		String priority = request.getParameter("priority");
		String summary = request.getParameter("summary");
		String description = request.getParameter("description");
		
		try{
	    	   	//Creating new ticket in the Databases
		    	boolean ticketCreated = false;
		    	TicketBean newTicket = new TicketBean();
		    	newTicket.setProject(project);
		    	newTicket.setStatus(status);
		    	newTicket.setPriority(priority);
		    	newTicket.setSummary(summary);
		    	newTicket.setDescription(description);
		    	newTicket.setCreatedBy(firstname + "" +lastname);
		    	newTicket.setUpdatedBy(firstname + "" +lastname);
		    	try{
		    		ticketCreated = TicketDAO.createTicket(newTicket);
		    	}catch(Exception ex){
		    		System.out.println("Error creating new ticket: "+ ex.getMessage());
		    		ex.printStackTrace();
		    	}
		    	
		    	if(ticketCreated){
		    		//Success-Forward to ticket list Page
		    		RequestDispatcher dispatcher = getServletConfig().getServletContext().getRequestDispatcher("/ListTickets.jsp");
		            dispatcher.forward(request,response);
		    	}else{
		    		//New ticket not created, alert user with error message!
		    		request.setAttribute("ERROR_MSG" , "Ticket not created.");
					RequestDispatcher dispatcher = getServletConfig().getServletContext().getRequestDispatcher("/createTicket.jsp");
				    dispatcher.forward(request,response);
		    	}
		    
	    }catch(Exception ex){
	    	System.out.println("Error creating new user: "+ ex.getMessage());
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
