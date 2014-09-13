package com.db.retryable;

import com.beans.TicketBean;
import com.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;

import com.beans.TicketBean;


public class ViewTicket {
	
	private static Logger log = Logger.getLogger( ViewTicket.class.getName());
	private String id;
	TicketBean ticket = new TicketBean();
	
	public ViewTicket(){
		super();
	}
	
	public ViewTicket(String id){
		this.id = id;	
		
	}
  
	public TicketBean viewTicket() throws Exception{
		log.debug("Entering method viewTicket() inside file viewTicket");
		Connection connection = null;
		PreparedStatement statement = null;        
		try {
			connection = DatabaseConnection.getConnection();
			String sqlQuery = "Select * from BugTracker.Tickets where id='"+this.id+"'";
	        statement = connection.prepareStatement(sqlQuery);
			ResultSet results = statement.executeQuery();
			if (results != null) {
				while (results.next()) {
					if(results.getString("Assignee") != null){
						ticket.setAssignee(results.getString("assignee"));
					}
					if(results.getString("ClientEmail") != null){
						ticket.setClientEmail(results.getString("ClientEmail"));
					}
					if(results.getString("Summary") != null){
						ticket.setSummary(results.getString("Summary"));
					}
					if(results.getString("Description") != null){
						ticket.setDescription(results.getString("Description"));
					}
					if(results.getString("Status") != null){
						ticket.setStatus(results.getString("Status"));
					}
					if(results.getString("ProjectName") != null){
						ticket.setProject(results.getString("ProjectName"));
					}
					if(results.getString("Priority") != null){
						ticket.setPriority(results.getString("Priority"));
					}
					if(results.getString("ID") != null){
						ticket.setId(results.getInt("ID"));
					}
					
				}
			}   
		} catch (Exception e) {
			log.debug("Error occured while checking for existing ticket: "+ e.getMessage());
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}finally{
			if(connection!=null){
				connection.close();
			}
			if(statement!=null){
				statement.close();
			}
		}
		log.debug("Exiting method viewTicket() inside file viewTicket");
		return ticket;        
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TicketBean newTicket = new TicketBean();
		ViewTicket ticket = new ViewTicket("1"); 
		try {
			newTicket = ticket.viewTicket();
			System.out.println(newTicket.getId());
			System.out.println(newTicket.getAssignee());
			System.out.println(newTicket.getPriority());
			System.out.println(newTicket.getDescription());
			System.out.println(newTicket.getSummary());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		
	}

}