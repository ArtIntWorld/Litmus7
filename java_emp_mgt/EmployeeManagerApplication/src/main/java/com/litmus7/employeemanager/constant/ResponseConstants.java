package com.litmus7.employeemanager.constant;

public class ResponseConstants {
    
    public static final int OK = 200;
    public static final int BAD_REQUEST = 400;
    public static final int PARTIAL_ERROR = 206;
    public static final int FULL_ERROR = 422;
    public static final int NO_CONTENT = 204;
    public static final int DATABASE_CONNECTION_ERROR = 503;
    
    public static final String EXPORT_SUCCESS_MESSSAGE = "Employee data exported successfully.";
    public static final String IMPORT_SUCCESS_MESSAGE = "Employees imported successfully.";
    public static final String INVALID_CSV_MESSAGE = "Invalid CSV format or unsupported file.";
    public static final String EMPTY_CSV_MESSAGE = "The uploaded CSV file is empty.";
    public static final String PARTIAL_VALID_ERROR_MESSAGE = "Some rows failed validation.";
    public static final String FULL_VALID_ERROR_MESSAGE = "All rows failed validation.";
    public static final String NO_CONTENT_MESSAGE = "No employee data provided.";
    public static final String ERROR_CSV_MESSAGE = "Unable to read the file.";
    public static final String REMOVE_SUCCESS_MESSAGE = "Employee has been removed from the database successfully.";
	public static final String UPDATE_SUCCESS_MESSAGE = "Employee has been updated into the database successfully.";
	public static final String ADD_SUCCESS_MESSAGE = "Added new employee details into the database.";
	public static final String NO_TRANSFER_MESSAGE = "No employees where transferred to the department.";
	public static final String PARTIAL_TRANSFER_MESSAGE = "Some employees where transferred to the department.";
	public static final String TRANSFER_MESSAGE = "All employees where transferred to the department.";
	public static final String DATABASE_CONNECTION_ERROR_MESSAGE = "Failed to connect to the database. Please try again later.";
}
