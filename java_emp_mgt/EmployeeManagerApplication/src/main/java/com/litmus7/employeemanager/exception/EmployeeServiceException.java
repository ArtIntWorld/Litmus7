package com.litmus7.employeemanager.exception;

public class EmployeeServiceException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer errorCode;
	
	public EmployeeServiceException(String message) {
		super(message);
	}
	
	public EmployeeServiceException(String message, Throwable cause) {
		super(message, cause);
		
	}
	
	public EmployeeServiceException(Integer errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}
	
	public EmployeeServiceException(Integer errorCode) {
		this.errorCode = errorCode;
	}
	
	public void setErrorCode(int errorCode) { this.errorCode = errorCode; }
	public int getErrorCode() { return errorCode; }
}
