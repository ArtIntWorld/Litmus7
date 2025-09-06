package com.litmus7.inventorymanagementsinglethread.dto;

public class Response<T> {
	
	private Integer statusCode;
	private String statusMessage;
	private T data;
	
	public Response(Integer statusCode, String statusMessage) {
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}
	
	public Response(Integer statusCode, String statusMessage, T data) {
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.data = data;
	}
	
	public void setStatusCode(Integer statusCode) { this.statusCode = statusCode; }
	public Integer getStatusCode() { return statusCode; }
	
	public void setStatusMessage(String statusMessage) {this.statusMessage = statusMessage; }
	public String getStatusMessage() { return statusMessage; }
	
	public void setData(T data) { this.data = data; }
	public T getData() { return data; }
	
}
