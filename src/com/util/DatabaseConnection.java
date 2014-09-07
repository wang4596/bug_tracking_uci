package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.db.retryable.LoginUser;

public class DatabaseConnection {
	private static Connection connection = null;
	private static Logger log = Logger.getLogger(LoginUser.class.getName());
	
	public static Connection getConnection(){
		try{
			log.debug("Loading database parameters from the property file....");
			Class.forName(PropFileUtil.getValue("DB_DRIVER"));
			String username = PropFileUtil.getValue("DB_USERNAME");
			String password = PropFileUtil.getValue("DB_PASSWORD");
			String urlString = PropFileUtil.getValue("DB_URL_STRING");
			connection =  DriverManager.getConnection(urlString,username, password);
			log.debug("Established database connection...");
		}
		catch (Exception e) {
			log.error("Error"+ e.getMessage());
		}
		return connection;
	}

	public void closeConnection(){
		if(connection != null){
			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Error while closing Database Connection Pool", e);
	        } finally {
	        	this.connection = null;
	        }
		}
	}
}