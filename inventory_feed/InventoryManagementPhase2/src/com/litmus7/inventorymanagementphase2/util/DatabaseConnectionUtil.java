package com.litmus7.inventorymanagementphase2.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionUtil{
	
	private static final Properties PROPERTIES = new Properties();
	private static final String PROPERTIES_FILE_NAME = "DatabaseConfiguration.properties";
	
	static {  
		loadProperties(); 
	}

	 private static void loadProperties() {
	        try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME);) {
	            if (input == null) {
	                System.err.println("Unable to find " + PROPERTIES_FILE_NAME + " in the classpath.");
	                throw new RuntimeException("Database properties file not found: " + PROPERTIES_FILE_NAME);
	            }
	            PROPERTIES.load(input);
	        } catch (IOException e) {
	            System.err.println("Failed to load database properties: " + e.getMessage());
	            throw new RuntimeException("Error reading database properties", e);
	        }
	    }
	
	public static Connection getConnection() throws SQLException{
		String url = PROPERTIES.getProperty("DB.url");
		String username = PROPERTIES.getProperty("DB.username");
		String password = PROPERTIES.getProperty("DB.password");
		
		return DriverManager.getConnection(url, username, password);
		
	}
}

