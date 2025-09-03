package com.litmus7.inventorymanagementphase2.exception;

public class InventoryServiceException extends Exception{

	private Integer errorCode;
	private static final long serialVersionUID = 3533963837000630590L;
	
	public InventoryServiceException(Integer errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}
	
	public InventoryServiceException(Integer errorCode) {
		this.errorCode = errorCode;
	}
	
	public void setErrorCode(Integer errorCode) { this.errorCode = errorCode; }
	public Integer getErrorCode() { return errorCode; }
	
}
