package com.litmus7.inventorymanagementsinglethread.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

import com.litmus7.inventorymanagementsinglethread.exception.InventoryException;

public class ApplicationErrorHandler {
	private static final Properties PROPERTIES = new Properties();
	private static final String PROPERTIES_FILE_NAME = "ApplicationErrorCode.properties";
	
	static {
		loadProperties();
	}

	private static void loadProperties() {
		try(InputStream input = InventoryException.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME);) {
			if(input == null) {
				throw new RuntimeException("Application Error file is not found : " + PROPERTIES_FILE_NAME);
			}
			PROPERTIES.load(input);
		}
		catch(IOException e) {
			throw new RuntimeException("Unable to open the file : " + e.getMessage());
		}
	}
	
	public static String getMessage(String key) {
		return PROPERTIES.getProperty(key);
	}
	
	public static String getMessage(String key, Object obj) {
		return MessageFormat.format(PROPERTIES.getProperty(key), obj);
	}
}
