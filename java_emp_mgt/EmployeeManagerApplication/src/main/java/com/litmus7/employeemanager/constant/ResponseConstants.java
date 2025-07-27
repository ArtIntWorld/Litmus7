package com.litmus7.employeemanager.constant;

public class ResponseConstants {
    
    public static final int OK = 200;
    public static final int BAD_REQUEST = 400;
    public static final int PARTIAL_VALID_ERROR = 206;
    public static final int FULL_VALID_ERROR = 422;
    
    public static final String EXPORT_SUCCESS_MESSSAGE = "Employee data exported successfully.";
    public static final String IMPORT_SUCCESS_MESSAGE = "Employees imported successfully.";
    public static final String INVALID_CSV_MESSAGE = "Invalid CSV format or unsupported file.";
    public static final String EMPTY_CSV_MESSAGE = "The uploaded CSV file is empty.";
    public static final String PARTIAL_VALID_ERROR_MESSAGE = "Some rows failed validation.";
    public static final String FULL_VALID_ERROR_MESSAGE = "All rows failed validation.";
    public static final String EMPTY_EMPLOYEE_MESSAGE = "No employee data provided.";
    
}
