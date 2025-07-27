package com.litmus7.employeemanager.controller;

import java.util.List;

import com.litmus7.employeemanager.constant.ResponseConstants;
import com.litmus7.employeemanager.dto.EmployeeDTO;
import com.litmus7.employeemanager.dto.EmployeeValidDTO;
import com.litmus7.employeemanager.dto.ResponseDTO;
import com.litmus7.employeemanager.service.EmployeeService;
import com.litmus7.employeemanager.util.ValidationUtil;

public class EmployeeController {
	
	public ResponseDTO<List<EmployeeDTO>> AllEmployeeDetails(){
		
		EmployeeService employeeService = new EmployeeService();
		List<EmployeeDTO> allemployees = employeeService.exportEmployeeDetails();
		
		if(allemployees == null) {
			return new ResponseDTO<>(ResponseConstants.OK, ResponseConstants.EMPTY_EMPLOYEE_MESSAGE, allemployees);
		}
		return new ResponseDTO<>(ResponseConstants.OK, ResponseConstants.IMPORT_SUCCESS_MESSAGE, allemployees);
	}
	
	public ResponseDTO<Integer> importEmployeeToDB(String filepath) {
		
		String message;
		EmployeeService employeeService = new EmployeeService();
		
		
		int readStatus = ValidationUtil.validateCSVFile(filepath);
		int statuscode = ResponseConstants.OK;
		
		int empCount = 0;
		
		if(readStatus == 0) {
			statuscode = ResponseConstants.BAD_REQUEST;
			message = ResponseConstants.EMPTY_CSV_MESSAGE;
			}
		else if(readStatus == 1) {
			statuscode = ResponseConstants.BAD_REQUEST;
			message = ResponseConstants.INVALID_CSV_MESSAGE;
		}
		else {
			EmployeeValidDTO importToDBResponse = employeeService.writeEmployeeToDB(filepath);
			boolean validStatus = importToDBResponse.getStatus();
			empCount = importToDBResponse.getDataCount();
			
			if (!validStatus && empCount == 0) {
				statuscode = ResponseConstants.FULL_VALID_ERROR;
				message = ResponseConstants.FULL_VALID_ERROR_MESSAGE;
			}
			else if (!validStatus) {
				statuscode = ResponseConstants.PARTIAL_VALID_ERROR;
				message = ResponseConstants.PARTIAL_VALID_ERROR_MESSAGE;
			}
			else {
				statuscode = ResponseConstants.OK;
				message = ResponseConstants.EXPORT_SUCCESS_MESSSAGE;
			}
		}
		
		return new ResponseDTO<>(statuscode ,message, empCount);
	}
}
