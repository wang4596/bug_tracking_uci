package com.db.retryable;

import com.beans.TicketBean;
import com.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class CreateTicket {
	
	private TicketBean newTicket = new TicketBean();
	
	public CreateTicket(TicketBean ticket){
		
		newTicket.setProject(ticket.getProject());
		newTicket.setStatus(ticket.getStatus());
		newTicket.setDescription(ticket.getDescription());
		newTicket.setSummary(ticket.getSummary());
		newTicket.setPriority(ticket.getPriority());
		newTicket.setCreatedBy(ticket.getCreatedBy());
		newTicket.setUpdatedBy(ticket.getUpdatedBy());
	}
	
	public boolean insertTicket() throws SQLException{
		Connection connection = DatabaseConnection.getConnection();
		String sqlQuery = null;
        	PreparedStatement statement = null;
        	int rowCount = 0;        		

        	sqlQuery = "INSERT INTO BugTracker.Tickets(ProjectName,Status, Description, Summary, Priority, Created_Date, Updated_Date,Created_by,Updated_by) " +
        		"VALUES(?,?,?,?,?,?,?,?,?)";
       
        	statement = connection.prepareStatement(sqlQuery);
        	statement.setString(1, newTicket.getProject());
        	statement.setString(2, newTicket.getStatus());
        	statement.setString(3, newTicket.getDescription());
        	statement.setString(4, newTicket.getSummary());
        	statement.setString(5, newTicket.getPriority());
        	statement.setTimestamp(6, new Timestamp(new Date().getTime()));
        	statement.setTimestamp(7, new Timestamp(new Date().getTime()));
        	statement.setString(8, newTicket.getCreatedBy());
        	statement.setString(9, newTicket.getUpdatedBy());
        	rowCount = statement.executeUpdate();
        	if(rowCount == 1){
        	return true;
       	 	}
        	return false;
        
		}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TicketBean ticket = new TicketBean();
		ticket.setDescription("Test");
		ticket.setPriority("medium");
		ticket.setProject("Project 1");
		ticket.setStatus("Open");
		ticket.setSummary("summary");
		CreateTicket newTicket = new CreateTicket(ticket);
		
		try {
			newTicket.insertTicket();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
		
	}
}

