package com.litmus7.employeemanager.controller;

import com.litmus7.employeemanager.constant.ResponseConstants;
import com.litmus7.employeemanager.dto.EmployeeValidDTO;
import com.litmus7.employeemanager.dto.ResponseDTO;
import com.litmus7.employeemanager.service.EmployeeService;
import com.litmus7.employeemanager.util.ValidationUtil;

public class EmployeeController {
	
	public ResponseDTO<Integer> importEmployeeToDB(String filepath) {
		
		String message;
		EmployeeService employeeService = new EmployeeService();
		
		
		int readStatus = ValidationUtil.validateCSVFile(filepath);
		int statuscode = ResponseConstants.SUCCESS;
		
		int empCount = 0;
		
		if(readStatus == 0) {
			statuscode = ResponseConstants.FILE_NOT_FOUND;
			message = ResponseConstants.FILE_NOT_FOUND_MSG;
			}
		else if(readStatus == 1) {
			statuscode = ResponseConstants.INVALID_CSV_FORMAT;
			message = ResponseConstants.INVALID_CSV_FORMAT_MSG;
		}
		else {
			EmployeeValidDTO importToDBResponse = employeeService.writeEmployeeToDB(filepath);
			boolean validStatus = importToDBResponse.getStatus();
			empCount = importToDBResponse.getDataCount();
			
			if (!validStatus && empCount == 0) {
				statuscode = ResponseConstants.VALIDATION_FAILED;
				message = ResponseConstants.FULL_VALIDATION_ERROR;
			}
			else if (!validStatus) {
				statuscode = ResponseConstants.VALIDATION_FAILED;
				message = ResponseConstants.PARTIAL_VALIDATION_ERROR;
			}
			else {
				statuscode = ResponseConstants.SUCCESS;
				message = ResponseConstants.SUCCESS_MSG;
			}
		}
		
		return new ResponseDTO<>(statuscode ,message, empCount);
	}
}
