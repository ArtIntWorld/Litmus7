package com.litmus7.employeemanager.dto;

import java.util.List;

public class EmployeeValidDTO {
	private boolean status;
	private List<String[]> data;
	private int dataCount;
	
	public EmployeeValidDTO(boolean status) {
		this.status = status;
	}
	
	public EmployeeValidDTO(boolean status, int dataCount) {
		this.status = status;
		this.dataCount = dataCount;
	}
	
	public EmployeeValidDTO(List<String[]> data) {
		this.data = data;
	}
	
	public boolean getStatus() { return status; }
	public void setStatus(boolean status) { this.status = status; }
	
	public List<String[]> getData() { return data; }
	public void setData(List<String[]> data) { this.data = data; }
	
	public int getDataCount() { return dataCount; }
	public void setDataCount(int dataCount) { this.dataCount = dataCount; }
	
}
