package com.litmus7.employeemanager.ui;

import com.litmus7.employeemanager.constant.ResponseConstants;
import com.litmus7.employeemanager.controller.EmployeeController;
import com.litmus7.employeemanager.dto.ResponseDTO;
import com.litmus7.employeemanager.dto.EmployeeServiceDTO;

public class Client {
	
	public static void main(String[] args) {
		EmployeeController request = new EmployeeController();
		
		String filepath = "D:\\Litmus7\\java_emp_mgt\\EmployeeManagerApplication\\resource\\employees.csv";
		ResponseDTO<EmployeeServiceDTO> response = request.importEmployeeToDB(filepath);
		int errorCount = 0;
		
		if(response.getStatus() == ResponseConstants.SUCCESS) {
			
			System.out.println("Message : "+response.getMessage());
			System.out.println("Entries in : "+response.getData().getEmployeeCount());
			
		} else if (response.getStatus() == ResponseConstants.VALIDATION_FAILED) {
			
			System.out.println("Message : "+ response.getMessage());
			
			for(String error : response.getData().getValidateError()) {
				errorCount++;
				System.out.println(" Error " + errorCount + " : " + error);
				
			}
			
			System.out.println();
			System.out.println("Entries in : "+response.getData().getEmployeeCount());
			
		}
		else {
			System.out.println("Message : "+response.getMessage());
		}
	}

}
