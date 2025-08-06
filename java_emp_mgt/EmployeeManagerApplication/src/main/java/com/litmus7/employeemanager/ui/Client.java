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
			System.out.println("Export Message : " + exportResponse.getMessage());
		}
		else if(exportResponse.getStatus() == 206) {
			System.out.println("Export Status : " + exportResponse.getStatus());
			System.out.println("Message : " + exportResponse.getMessage());
			System.out.println("Successful Entries : " + exportResponse.getData());
		}
		else {
			System.out.println("Export Status : " + exportResponse.getStatus());
			System.out.println("Message : " + exportResponse.getMessage());
		}
		
		System.out.println("---------------------------------------------------------------------------------------------------------------");
		
		ResponseDTO<List<EmployeeDTO>> getEmployeesResponse = request.getAllEmployees();
		
		
		if(getEmployeesResponse.getStatus() != 200) {
			System.out.println("Import Status : " + getEmployeesResponse.getStatus());
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
		
		System.out.println("---------------------------------------------------------------------------------------------------------------");
		ResponseDTO<Void> deleteEmployeeResponse = request.removeEmployeeFromDB(303);
		
		System.out.println("Delete Status : " + deleteEmployeeResponse.getStatus());
		System.out.println("Message : " + deleteEmployeeResponse.getMessage());
		
		System.out.println("---------------------------------------------------------------------------------------------------------------");
		
		String[] inputEmployee = {"304", "E", "V", "ev@gmail.com", "9509453916", "Marketing", "12000.0", "20-09-1999"};
		
		ResponseDTO<Void> updateEmployeeResponse = request.updateExistingEmployee(inputEmployee);
		
		System.out.println("Update Status : " + updateEmployeeResponse.getStatus());
		System.out.println("Message : " + updateEmployeeResponse.getMessage());
		
		System.out.println("---------------------------------------------------------------------------------------------------------------");
		ResponseDTO<EmployeeDTO> retrieveEmployeeResponse = request.retrieveEmployeeByID(304);
		EmployeeDTO employee = retrieveEmployeeResponse.getData();
		
		if(retrieveEmployeeResponse.getStatus() == 200) {
			Integer id;
			String first_name, last_name, email, phone, department;
			double salary;
			Date join_date;
			
			id = employee.getID();
			first_name = employee.getFirstName();
			last_name = employee.getLastName();
			email = employee.getEmail();
			phone = employee.getPhone();
			department = employee.getDepartment();
			salary = employee.getSalary();
			join_date = employee.getJoinDate();
			
			System.out.println(id + "\t" + first_name + " " + last_name + "\t" + email + "\t" + phone + "\t" + department + "\t" + salary + "\t" + join_date);
		}
		else {
			System.out.println("Retrieve by ID Status : " + retrieveEmployeeResponse.getStatus());
			System.out.println("Message : " + retrieveEmployeeResponse.getMessage());
		}
		
		System.out.println("---------------------------------------------------------------------------------------------------------------");
		
		String[] addinputEmployee = {"13", "Anjali", "Menon", "anjali.menon@gmail.com", "9876543210", "Finance", "25000.0", "15-05-2020"};
		
		ResponseDTO<Void> addEmployeeResponse = request.addNewEmployee(addinputEmployee);
		
		System.out.println("Update Status : " + addEmployeeResponse.getStatus());
		System.out.println("Message : " + addEmployeeResponse.getMessage());
	}

}
