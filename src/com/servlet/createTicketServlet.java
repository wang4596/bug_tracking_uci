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
import com.util.PropFileUtil;
import com.util.SendMailTLS;

public class createTicketServlet extends HttpServlet{
   
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{	
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
		String firstname =  session.getAttribute("firstName").toString();
	    String lastname =  session.getAttribute("lastName").toString();
	    String project = request.getParameter("project");
		String status = request.getParameter("status");
		String priority = request.getParameter("priority");
		String summary = request.getParameter("summary");
		String description = request.getParameter("description");
		String assigneeEmail = request.getParameter("assignee");
		String clientEmail = request.getParameter("clientEmail");
		
		try{
	    	   	//Creating new ticket in the Databases
		    	boolean ticketCreated = false;
		    	TicketBean newTicket = new TicketBean();
		    	newTicket.setProject(project);
		    	newTicket.setStatus(status);
		    	newTicket.setPriority(priority);
		    	newTicket.setSummary(summary);
		    	newTicket.setDescription(description);
		    	newTicket.setCreatedBy(firstname + " " +lastname);
		    	newTicket.setUpdatedBy(firstname + " " +lastname);
		    	newTicket.setAssignee(assigneeEmail);
		    	newTicket.setClientEmail(clientEmail);
		    	try{
		    		ticketCreated = TicketDAO.createTicket(newTicket);
	    		

		    		PropFileUtil.load();
		    		StringBuilder emailBody = new StringBuilder("");
					emailBody.append("Hello,");
					emailBody.append("\n");
					emailBody.append("We will be in touch with you if there are any questions.");
					SendMailTLS.SendEmail(PropFileUtil.getValue("MAIL_USERNAME"), clientEmail, "Your defect request has been submitted", emailBody.toString());
		    							
					emailBody = new StringBuilder("");
					emailBody.append("Hello,");
					emailBody.append("\n");
					emailBody.append("A new request has been assigned to you. Please check your open tickets list");
					SendMailTLS.SendEmail(PropFileUtil.getValue("MAIL_USERNAME"), assigneeEmail, "New request", emailBody.toString());
	    		
		    		
		    	}catch(Exception ex){
		    		System.out.println("Error creating new ticket: "+ ex.getMessage());
		    		ex.printStackTrace();
		    	}
		    	
		    	if(ticketCreated){
		    		//Success-Forward to ticket list Page
		    		RequestDispatcher dispatcher = getServletConfig().getServletContext().getRequestDispatcher("/HomePage.jsp");
		            dispatcher.forward(request,response);
		    	}else{
		    		//New ticket not created, alert user with error message!
		    		request.setAttribute("ERROR_MSG" , "Ticket not created.");
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
