package com.litmus7.employeemanager.ui;

import java.sql.Date;
import java.util.List;

import com.litmus7.employeemanager.controller.EmployeeController;
import com.litmus7.employeemanager.dto.ResponseDTO;
import com.litmus7.employeemanager.dto.EmployeeDTO;

public class Client {
	
	public static void main(String[] args) {
		EmployeeController request = new EmployeeController();
		
		String filepath = "D:\\Litmus7\\java_emp_mgt\\EmployeeManagerApplication\\resource\\employee1.csv";
		ResponseDTO<Integer> exportResponse = request.writeEmployeeToDB(filepath);
		
		if(exportResponse.getStatus() == 200) {
			System.out.println("Message : " + exportResponse.getMessage());
		}
		else if(exportResponse.getStatus() == 206) {
			System.out.println("Status : " + exportResponse.getStatus());
			System.out.println("Message : " + exportResponse.getMessage());
			System.out.println("Successful Entries : " + exportResponse.getData());
		}
		else {
			System.out.println("Status : " + exportResponse.getStatus());
			System.out.println("Message : " + exportResponse.getMessage());
		}
		
		System.out.println();
		
		ResponseDTO<List<EmployeeDTO>> getEmployeesResponse = request.getAllEmployees();
		
		
		if(getEmployeesResponse.getStatus() != 200) {
			System.out.println("Status : " + getEmployeesResponse.getStatus());
			System.out.println("Message : " + getEmployeesResponse.getMessage());
		} else {	
			
			List<EmployeeDTO> employees = getEmployeesResponse.getData();
			
			Integer id;
			String first_name, last_name, email, phone, department;
			double salary;
			Date join_date;
			
			System.out.println("ID\tName\t\tEmail\t\tPhone\t\tDepartment\tSalary\t\tJoining Date");
			for(EmployeeDTO employee : employees) {
				
				id = employee.getID();
				first_name = employee.getFirstName();
				last_name = employee.getLastName();
				email = employee.getEmail();
				phone = employee.getPhone();
				department = employee.getDepartment();
				salary = employee.getSalary();
				join_date = employee.getJoinDate();
				
				System.out.println(id + "\t" + first_name + " " + last_name + "\t\t" + email + "\t" + phone + "\t" + department + "\t\t" + salary + "\t\t" + join_date);
			}
		}
	}

}
