package com.litmus7.employeemanager.ui;

import com.litmus7.employeemanager.constant.ResponseConstants;
import com.litmus7.employeemanager.controller.EmployeeController;
import com.litmus7.employeemanager.dto.ResponseDTO;

public class Client {
	
	public static void main(String[] args) {
		EmployeeController request = new EmployeeController();
		
		String filepath = "D:\\Litmus7\\java_emp_mgt\\EmployeeManagerApplication\\resource\\employee1.csv";
		ResponseDTO<Integer> response = request.importEmployeeToDB(filepath);
		
		if(response.getStatus() == ResponseConstants.SUCCESS) {
			
			System.out.println("Message : "+response.getMessage());
			System.out.println("Entries in : "+response.getData());
			
		} else if (response.getStatus() == ResponseConstants.VALIDATION_FAILED) {
			
			System.out.println("Message : "+ response.getMessage());
			System.out.println("Entries in : "+response.getData());
			
		}
		else {
			System.out.println("Message : "+response.getMessage());
		}
	}

}
