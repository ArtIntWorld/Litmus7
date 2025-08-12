package com.litmus7.retailstoremanagement.exception;

public class RetailStoreServiceException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public RetailStoreServiceException(String message) {
		super(message);
	}

	public RetailStoreServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
