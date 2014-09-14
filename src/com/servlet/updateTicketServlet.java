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

public class updateTicketServlet extends HttpServlet{
   
	private static final long serialVersionUID = 1L;
	 
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{	
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
	    String firstname = (String) session.getAttribute("firstName");
	    String lastname = (String) session.getAttribute("lastname");
	   
	    String ticketid = request.getParameter("ticketid");
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
		    	newTicket.setProject(project);
		    	newTicket.setStatus(status);
		    	newTicket.setPriority(priority);
		    	newTicket.setSummary(summary);
		    	newTicket.setDescription(description);
		    	newTicket.setUpdatedBy(firstname + "" +lastname);
		    	newTicket.setAssignee(assigneeEmail);
		    	newTicket.setClientEmail(clientEmail);
		    	
		    	try{
		    		
		    		ticketCreated = TicketDAO.updateTicket(newTicket,ticketid);
		    		
		    		PropFileUtil.load();
		    		StringBuilder emailBody = new StringBuilder("");
		    		emailBody.append("Hello,");
					emailBody.append("\n");
					emailBody.append("We will be in touch with you if there are any questions.");
					SendMailTLS.SendEmail(PropFileUtil.getValue("MAIL_USERNAME"), clientEmail, "Your defect request#"+ticketid+" has been updated", emailBody.toString());
		    		
					emailBody = new StringBuilder("");
					emailBody.append("Hello,");
					emailBody.append("\n");
					emailBody.append("Request# "+ticketid+" has been assigned to you. Please check your open tickets list");
					SendMailTLS.SendEmail(PropFileUtil.getValue("MAIL_USERNAME"), assigneeEmail, "Your defect request#"+ticketid+" has been updated" , emailBody.toString());
		    		
		    		
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
