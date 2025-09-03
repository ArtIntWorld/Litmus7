package com.litmus7.inventorymanagementphase2.exception;

public class CSVUtilException extends Exception{
	private Integer errorCode;
	private static final long serialVersionUID = 3533963837000630590L;
	
	public CSVUtilException(Integer errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}
	
	public CSVUtilException(Integer errorCode) {
		this.errorCode = errorCode;
	}
	
	public void setErrorCode(Integer errorCode) { this.errorCode = errorCode; }
	public Integer getErrorCode() { return errorCode; }
}
