package com.litmus7.employeemanager.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

import com.litmus7.employeemanager.exception.EmployeeServiceException;

public class ErrorMessageLoader {
	
	private static final Properties PROPERTIES = new Properties();
    private static final String PROPERTIES_FILE_NAME = "com/litmus7/employeemanager/property/ApplicationErrorCode.properties";
	
    static {
        loadProperties();
    }
    
    private static void loadProperties() {
        try (InputStream input = EmployeeServiceException.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME);) {
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
    
    public static String getMessage(String key) {
		return PROPERTIES.getProperty(key);
	}
	public static String getMessage(String key,Object obj) {
		return MessageFormat.format(PROPERTIES.getProperty(key),obj);
	}
}
