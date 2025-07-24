package com.litmus7.employeemanager.dto;

import java.sql.Date;

public class EmployeeDTO {
	private Integer id;
	private String first_name, last_name, email, phone, department;
	private double salary;
	private Date join_date;
	
	public EmployeeDTO() { }
	
	public EmployeeDTO(Integer id, String first_name, String last_name, String email, String phone, String department, double salary, Date join_date) {
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.phone = phone;
		this.department = department;
		this.salary = salary;
		this.join_date = join_date;
	}
	
	public Integer getID() { return id; }
	public void setID(Integer id) { this.id = id; }
	
	public String getFirstName() { return first_name; }
	public void setFirstName(String first_name) { this.first_name = first_name; }
	
	public String getLastName() { return last_name; }
	public void setLastName(String last_name) { this.last_name = last_name; }
	
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	
	public String getPhone() { return phone; }
	public void setPhone(String phone) { this.phone = phone; }
	
	public String getDepartment() { return department; }
	public void setDepartment(String department) { this.department = department; }
	
	public double getSalary() { return salary; }
	public void setSalary(double salary) { this.salary = salary; }
	
	public Date getJoinDate() { return join_date; }
	public void setJoinDate(Date join_date) { this.join_date = join_date; }
}
