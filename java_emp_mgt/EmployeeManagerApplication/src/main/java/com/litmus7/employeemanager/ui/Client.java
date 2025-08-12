package com.litmus7.employeemanager.ui;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import com.litmus7.employeemanager.controller.EmployeeController;
import com.litmus7.employeemanager.dto.ResponseDTO;
import com.litmus7.employeemanager.dto.EmployeeDTO;

public class Client {
	
	public static void main(String[] args) {
		EmployeeController request = new EmployeeController();
		
		String filepath = "data/employee1.csv";
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
		
		System.out.println("---------------------------------------------------------------------------------------------------------------");
		
		List<String[]> addInputEmployees = Arrays.asList(
				new String[]{"13", "Anjali", "Menon", "anjali.menon@gmail.com", "9876543210", "Finance", "25000.0", "15-05-2020"},
			    new String[]{"14", "Rahul", "Nair", "rahul.nair@gmail.com", "9123456780", "HR", "22000.0", "01-03-2019"},
			    new String[]{"15", "Divya", "Krishnan", "divya.k@gmail.com", "9988776655", "IT", "28000.0", "20-07-2021"},
			    new String[]{"16", "Arjun", "Ravi", "arjun.ravi@gmail.com", "9876512340", "Marketing", "24000.0", "10-10-2022"},
			    new String[]{"17", "Meera", "Suresh", "meera.suresh@gmail.com", "9567890345", "Sales", "23000.0", "05-02-2018"},
				
			    // Valid row for reference
			    new String[]{"21", "Nisha", "George", "nisha.george@gmail.com", "9845012345", "Logistics", "26000.0", "12-09-2019"},

			    // Duplicate ID (21 repeated)
			    new String[]{"21", "Kiran", "Joseph", "kiran.joseph@gmail.com", "9999999999", "Admin", "24000.0", "11-11-2020"},

			    // Missing department and salary
			    new String[]{"22", "Sneha", "Das", "sneha.das@gmail.com", "9887766554", "", "", "18-06-2022"},

			    // Invalid email (no domain)
			    new String[]{"23", "Vimal", "Shaji", "vimal.shaji@", "9801234567", "Tech", "27000.0", "01-01-2023"},

			    // Invalid phone number (too short)
			    new String[]{"24", "Arya", "Nandan", "arya.nandan@gmail.com", "12345", "Design", "21000.0", "10-10-2020"},

			    // Invalid salary (contains symbols)
			    new String[]{"25", "Harish", "P", "harish.p@gmail.com", "9876541230", "Support", "$30000", "05-07-2018"},

			    // Invalid date (non-date string)
			    new String[]{"26", "Gita", "Ravi", "gita.ravi@gmail.com", "9765432198", "Operations", "28000.0", "soon"},

			    // Too few columns (missing join date)
			    new String[]{"27", "Ajay", "M", "ajay.m@gmail.com", "9000000000", "IT", "27000.0"}
			);


		
		ResponseDTO<Integer> addResponse = request.addEmployeeInBatch(addInputEmployees);
		
		if(addResponse.getStatus() == 200) {
			System.out.println("Export Message : " + addResponse.getMessage());
		}
		else if(addResponse.getStatus() == 206) {
			System.out.println("Export Status : " + addResponse.getStatus());
			System.out.println("Message : " + addResponse.getMessage());
			System.out.println("Successful Entries : " + addResponse.getData());
		}
		else {
			System.out.println("Export Status : " + addResponse.getStatus());
			System.out.println("Message : " + addResponse.getMessage());
		}
		
		System.out.println("---------------------------------------------------------------------------------------------------------------");
		
		List<Integer> employeeIds = Arrays.asList(12, 13, 17);
		
		ResponseDTO<Integer> transferDepartmentResponse = request.transferEmployeesToDepartment(employeeIds, "Marketing");
		
		if(transferDepartmentResponse.getStatus() == 200) {
			System.out.println("Message : " + transferDepartmentResponse.getMessage());
		} else if(transferDepartmentResponse.getStatus() == 206){
			System.out.println("Transfer Status : " + transferDepartmentResponse.getStatus());
			System.out.println("Message : " + transferDepartmentResponse.getMessage());
			System.out.println("Transferred Employees Count : " + transferDepartmentResponse.getData());
		} else {
		System.out.println("Transfer Status : " + transferDepartmentResponse.getStatus());
		System.out.println("Message : " + transferDepartmentResponse.getMessage());
		}
	}

}
