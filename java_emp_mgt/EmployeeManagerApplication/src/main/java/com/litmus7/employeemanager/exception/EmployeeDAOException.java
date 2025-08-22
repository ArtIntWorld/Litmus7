package com.litmus7.employeemanager.exception;

public class EmployeeDAOException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int errorCode;
	
	public EmployeeDAOException(String message) {
		super(message);
	}
	
	public EmployeeDAOException(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public EmployeeDAOException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public EmployeeDAOException(Integer errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}
	
	public void setErrorCode(int errorCode) { this.errorCode = errorCode; }
	public int getErrorCode() { return errorCode; }
}
