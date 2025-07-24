package com.litmus7.employeemanager.controller;

import com.litmus7.employeemanager.constant.ResponseConstants;
import com.litmus7.employeemanager.dto.EmployeeServiceDTO;
import com.litmus7.employeemanager.dto.ResponseDTO;
import com.litmus7.employeemanager.service.EmployeeService;

public class EmployeeController {
	
	public ResponseDTO<EmployeeServiceDTO> importEmployeeToDB(String filepath) {
		
		String message;
		EmployeeService employeeService = new EmployeeService();
		EmployeeServiceDTO importToDBResponse = employeeService.writeEmployeeToDB(filepath);
		
		int status = importToDBResponse.getStatus();
		
		if(status == ResponseConstants.VALIDATION_FAILED) {
			if(importToDBResponse.getEmployeeCount() != 0) {
				message = ResponseConstants.PARTIAL_VALIDATION_ERROR;
			}
			else {
				message = ResponseConstants.FULL_VALIDATION_ERROR;
			}
		}
		else if(importToDBResponse.getStatus() == ResponseConstants.FILE_NOT_FOUND) {
			message = ResponseConstants.CSV_ERROR;
		}
		else {
			message = ResponseConstants.SUCCESS_MSG;
		}
		
		return new ResponseDTO<>(status ,message, importToDBResponse);
		
	}
}
