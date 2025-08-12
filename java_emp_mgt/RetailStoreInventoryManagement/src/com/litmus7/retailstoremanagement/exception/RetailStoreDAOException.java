package com.litmus7.retailstoremanagement.exception;

public class RetailStoreDAOException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public RetailStoreDAOException(String message) {
		super(message);
	}

	public RetailStoreDAOException(String message, Throwable cause) {
		super(message, cause);
	}
}
