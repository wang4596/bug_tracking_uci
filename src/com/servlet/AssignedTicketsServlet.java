/*
 * Team D Bug Tracker Project
 * 9/7/2014		Melissa Stratton		Created File.
 */

package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import com.util.DatabaseConnection;
import com.beans.TicketBean;


import java.sql.*;
import java.util.*;

// This servlet will query the database for open tickets assigned to the current user.
public class AssignedTicketsServlet extends HttpServlet {
	private static final long serialVersionUID = 2L;
	private static Logger log = Logger.getLogger(LoginServlet.class.getName());
	
	public AssignedTicketsServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// Form SQL Query string
		String sqlQuery = "SELECT Id,Summary,Status,ProjectName,Priority,Assignee FROM BugTracker.Tickets WHERE status='Open' AND assignee='"
				+ request.getSession().getAttribute("emailID") + "';";
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;
		ArrayList<TicketBean> tickets = new ArrayList<TicketBean>();
		
		try {
			// Get a connection to the database and execute the SQL query.
			connection = DatabaseConnection.getConnection();
	        statement = connection.prepareStatement(sqlQuery);
	        log.debug("SQL: " + sqlQuery);
			results = statement.executeQuery();
			if (results != null)
			{
				// If there are results put them into a TicketBean and add the Bean to an ArrayList
		        log.debug("ResultSet not null");
		        while (results.next()) {
					TicketBean ticket = new TicketBean();
					ticket.setId(Integer.parseInt(results.getString(1)));
					ticket.setSummary(results.getString(2));
					ticket.setStatus(results.getString(3));
					ticket.setProject(results.getString(4));
					ticket.setPriority(results.getString(5));
					ticket.setAssignee(results.getString(6));
			        log.debug("Ticket ID: " + Integer.parseInt(results.getString(1)));
					tickets.add(ticket);
				}
		        // Add the tickets ArrayList as an attribute to the request.
		        request.setAttribute("tickets", tickets);
			}

			// Close the database connection.
			if(connection!=null){
				connection.close();
			}
			if(statement!=null){
				statement.close();
			}
		} catch (Exception e) {
			log.error("Error occured while checking for open tickets: "+ e.getMessage());
			e.printStackTrace();
		}
		// Forward on the request.
        RequestDispatcher rd = request.getRequestDispatcher("/AssignedTickets.jsp");
        rd.forward(request, response);
	} 
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
