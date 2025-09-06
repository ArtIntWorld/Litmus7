package com.litmus7.inventorymanagementphase3.exception;

public class InventoryDaoException extends Exception {
	private Integer errorCode;
	private static final long serialVersionUID = 3533963837000630590L;
	
	public InventoryDaoException(Integer errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}
	
	public InventoryDaoException(Integer errorCode) {
		this.errorCode = errorCode;
	}
	
	public void setErrorCode(Integer errorCode) { this.errorCode = errorCode; }
	public Integer getErrorCode() { return errorCode; }
}
