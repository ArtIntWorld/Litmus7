package com.litmus7.inventorymanagementsinglethread.exception;

public class InventoryException extends Exception{

	/**
	 * 
	 */
	
	private Integer errorCode;
	private static final long serialVersionUID = 3533963837000630590L;
	
	public InventoryException(Integer errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}
	
	public InventoryException(Integer errorCode) {
		this.errorCode = errorCode;
	}
	
	public void setErrorCode(Integer errorCode) { this.errorCode = errorCode; }
	public Integer getErrorCode() { return errorCode; }
	
}
