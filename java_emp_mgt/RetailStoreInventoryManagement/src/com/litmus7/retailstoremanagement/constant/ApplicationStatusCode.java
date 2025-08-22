package com.litmus7.retailstoremanagement.constant;

public class ApplicationStatusCode {	
	public static final int SUCCESS = 200;
	public static final int BAD_REQUEST = 400;
    public static final int NOT_FOUND = 404;
    public static final int INTERNAL_SERVER_ERROR = 500;
	    
    public static final String IMPORT_SUCCESS = "Product imported successfully";
    public static final String IMPORT_FAILURE = "Failed to import product";

    public static final String EXPORT_SUCCESS = "Products exported successfully";
    public static final String EXPORT_FAILURE = "Failed to export products";
    
    public static final String FILE_READ_SUCCESS = "The file was read successfully.";
    public static final String FILE_READ_FAILURE = "We could not read the file. Please check the file and try again.";

	public static final String FILE_WRITE_SUCCESS = "The file was saved successfully.";
    public static final String FILE_WRITE_FAILURE = "We could not save the file. Please try again.";
}