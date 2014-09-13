package com.db.dao;

import com.beans.TicketBean;
import com.db.retryable.CreateTicket;

public class TicketDAO {
 
	public static boolean updateTicket(TicketBean ticket, String ticketid) throws Exception{	
		CreateTicket newTicket = new CreateTicket(ticket, ticketid);
		return newTicket.updateTicket();	
	}	
	
	public static boolean createTicket(TicketBean ticket) throws Exception{	
		CreateTicket newTicket = new CreateTicket(ticket);
		return newTicket.insertTicket();	
	}	
	 
	 
} 
