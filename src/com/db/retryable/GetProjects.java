package com.db.retryable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import org.apache.log4j.Logger;

import com.util.DatabaseConnection;

public class GetProjects {
	
	private static Logger log = Logger.getLogger(GetUser.class.getName());
	
	public GetProjects(){
		super();
	}
	
	public ArrayList<String> listAllProjects()throws Exception {
		
		log.debug("Entering method listAllProjects() inside file GetProjects");
		Connection connection = null;
		PreparedStatement statement = null;    
		ArrayList<String> projects = new ArrayList<String>();
		String projectName="";

		try {
			connection = DatabaseConnection.getConnection();
			String sqlQuery = "Select * from BugTracker.Projects";
	        statement = connection.prepareStatement(sqlQuery);
			ResultSet results = statement.executeQuery();
			if (results != null) {
				while (results.next()) {
					if(results.getString("ProjectName") != null){
						projectName = results.getString("ProjectName");
						projects.add(projectName);
					}
			}
			}   
		} catch (Exception e) {
			log.debug("Error occured while retriving projects: "+ e.getMessage());
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
		log.debug("Exiting method listAllProjects() inside file GetProject");
		return projects;        
		
	}
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> projects = new ArrayList<String>();
		GetProjects g =  new GetProjects();
		try {
			projects = g.listAllProjects();
			
			for(String d:projects) {
	            System.out.println(d);
	            
	        }

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
