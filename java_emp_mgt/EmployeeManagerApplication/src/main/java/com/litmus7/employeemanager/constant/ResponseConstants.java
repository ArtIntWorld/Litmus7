package com.litmus7.employeemanager.constant;

public class ResponseConstants {
	public static final int SUCCESS = 0;
    public static final int FILE_NOT_FOUND = 1001;
    public static final int INVALID_FORMAT = 1002;
    public static final int IO_ERROR = 1003;
    public static final int VALIDATION_FAILED = 1004;
    public static final int DATABASE_ERROR = 1005;
    
    public static final String CSV_ERROR = "CSV file type is invalid or empty";
    public static final String PARTIAL_VALIDATION_ERROR = "Partial import due to validation problems.";
    public static final String FULL_VALIDATION_ERROR = "No import due to validation problems.";
    public static final String SUCCESS_MSG = "Successfully loaded the employee data to database.";
}
