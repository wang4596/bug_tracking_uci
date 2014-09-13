package com.servlet;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;
import com.beans.TicketBean;
import com.db.dao.TicketDAO;
import com.db.retryable.*;


public class ViewTicketServlet extends HttpServlet{
   
	private static final long serialVersionUID = 1L;
	 
	
	public ViewTicketServlet() {
		super();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{	
		
		String id = request.getParameter("ticketId");
				
		try{
	    	   	//Retrieving the ticket from the Database
		    	boolean ticketRetrieved = false;
		    	TicketBean newTicket = new TicketBean();
		    			    	
		    	try{
		    		
		    		ViewTicket ticket = new ViewTicket(id); 
		    		newTicket = ticket.viewTicket();	
		    		ticketRetrieved = true;
		    		
		    	}catch(Exception ex){
		    		System.out.println("Error creating new ticket: "+ ex.getMessage());
		    		ex.printStackTrace();
		    	}
		    	
		    	if(ticketRetrieved){
		    		//Success-Forward to view Ticket Page
		    		RequestDispatcher dispatcher = getServletConfig().getServletContext().getRequestDispatcher("/viewTicket.jsp");
		    		request.setAttribute("id", newTicket.getId());
		    		request.setAttribute("assignee", newTicket.getAssignee());
		    		request.setAttribute("clientEmail", newTicket.getClientEmail());
		    		request.setAttribute("summary", newTicket.getSummary());
		    		request.setAttribute("description", newTicket.getDescription());
		    		request.setAttribute("status", newTicket.getStatus());
		    		request.setAttribute("project", newTicket.getProject());
		    		request.setAttribute("priority", newTicket.getPriority());
		    		dispatcher.forward(request,response);
		    	}else{
		    		//New ticket not created, alert user with error message!
		    		request.setAttribute("ERROR_MSG" , "Ticket not retrieved.");
					RequestDispatcher dispatcher = getServletConfig().getServletContext().getRequestDispatcher("/createTicket.jsp");
				    dispatcher.forward(request,response);
		    	}
		    
	    }catch(Exception ex){
	    	System.out.println("Error creating new ticket: "+ ex.getMessage());
	    	ex.printStackTrace();
	    }        
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("This is a test");
		TicketBean newTicket = new TicketBean();
    	newTicket.setProject("Project 1");
    	newTicket.setStatus("Open");
    	newTicket.setPriority("Medium");
    	newTicket.setAssignee("Mary Wang");
    	newTicket.setSummary("Summary");
    	newTicket.setDescription("description");
    	newTicket.setCreatedBy("test");
    	newTicket.setUpdatedBy("test");
    	try {
			TicketDAO.createTicket(newTicket);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
