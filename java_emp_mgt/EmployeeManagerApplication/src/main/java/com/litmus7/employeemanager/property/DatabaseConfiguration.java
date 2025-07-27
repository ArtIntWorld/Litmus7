package com.litmus7.employeemanager.property;

public class DatabaseConfiguration {
	private String url;
    private String user;
    private String password;
    
    public DatabaseConfiguration(String url, String user, String password) {
    	this.url = url;
    	this.user = user;
    	this.password = password;
    }
    
    public static DatabaseConfiguration getDatabaseConfiguration() {
    	String url = "jdbc:mysql://localhost:3306/db_employee_details";
    	String user = "root";
    	String password = "Abdu12344!";
    	
    	return new DatabaseConfiguration(url, user, password);
    }
    
    public String getURL() { return url; }
    public String getUser() { return user; }
    public String getPassword() {return password; }
}
