package com.db.retryable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.util.DatabaseConnection;

public class GetAssignee {
	
	private static Logger log = Logger.getLogger(GetUser.class.getName());
	
	public GetAssignee(){
		super();
	}
	
	//public ArrayList<String> listAllAssignee()throws Exception {
	public HashMap<String,String> listAllAssignee()throws Exception {
		 
		log.debug("Entering method listAllAssignee() inside file GetAssignee");
		Connection connection = null;
		PreparedStatement statement = null;    
		//ArrayList<String> assignee = new ArrayList<String>();
		HashMap<String,String> assignee = new HashMap<String,String>();
		String name = "";
		String email ="";

		try {
			connection = DatabaseConnection.getConnection();
			String sqlQuery = "Select * from BugTracker.Users where role = 'team lead'";
	        statement = connection.prepareStatement(sqlQuery);
			ResultSet results = statement.executeQuery();
			if (results != null) {
				while (results.next()) {
					if(results.getString("firstname") != null){
						name = results.getString("firstname") + " "+ results.getString("lastname");
						email = results.getString("emailID");
						//assignee.add(name);
						assignee.put(email, name);
					}
				}
			}   
		} catch (Exception e) {
			log.debug("Error occured while retreiving assignees: "+ e.getMessage());
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
		log.debug("Exiting method listAllAssignee() inside file GetAssignee");
		return assignee;        
		
	}
	
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ArrayList<String> assignee = new ArrayList<String>();
		HashMap<String, String> assignee = new HashMap<String, String>();
		
		GetAssignee g =  new GetAssignee();
		try {
			assignee = g.listAllAssignee();
			Set<Entry<String, String>> set = assignee.entrySet();
		    Iterator<Entry<String, String>> i = set.iterator();
		    while(i.hasNext()) {
		         Entry<String, String> entry = i.next();
		         System.out.print(entry.getKey() + ": ");
		         System.out.println(entry.getValue());
		      }
			
			//for(String d:assignee) {
	            //System.out.println(d);
	            
	        //}

		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
	}

}
