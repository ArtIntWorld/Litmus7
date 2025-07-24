package com.litmus7.employeemanager.dto;

import java.util.List;

public class EmployeeServiceDTO {
	private int count;
	private int status;
	private List<String> validateError;
	private List<String[]> employeeData;
	
	public EmployeeServiceDTO() { }
	
	public EmployeeServiceDTO(int status, int count) {
		this.status=status;
		this.count = count;
	}
	
	public EmployeeServiceDTO(int count, int status, List<String[]> employeeData, List<String> validateError) {
		this.count = count;
		this.status = status;
		this.employeeData = employeeData;
		this.validateError = validateError;
	}
	
	public EmployeeServiceDTO(int status, List<String[]> employeeData, List<String> validateError) {
		this.status = status;
		this.employeeData = employeeData;
		this.validateError = validateError;
	}
	
	public int getEmployeeCount() { return count; }
	public void setEmployeeCount(int count) { this.count = count; }
	
	public int getStatus() { return status; }
	public void setSatus(int status) { this.status = status; }
	
	public List<String> getValidateError() { return validateError; }
	public void setValidateError(List<String> validateError) { this.validateError = validateError; }
	
	public List<String[]> getEmployeeData() { return employeeData; }
	public void setEmployeeData(List<String[]> employeeData) { this.employeeData = employeeData; }
}
