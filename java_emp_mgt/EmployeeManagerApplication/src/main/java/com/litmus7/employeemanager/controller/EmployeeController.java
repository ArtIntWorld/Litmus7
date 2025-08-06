package com.litmus7.employeemanager.controller;

import java.util.HashMap;
import java.util.List;

import com.litmus7.employeemanager.constant.ResponseConstants;
import com.litmus7.employeemanager.dto.EmployeeDTO;
import com.litmus7.employeemanager.dto.ResponseDTO;
import com.litmus7.employeemanager.exception.EmployeeServiceException;
import com.litmus7.employeemanager.service.EmployeeService;

public class EmployeeController {
	
	EmployeeService employeeService = new EmployeeService();
	
	public ResponseDTO<List<EmployeeDTO>> getAllEmployees(){
		List<EmployeeDTO> employees = null;
		
		try {
			employees = employeeService.exportEmployeeDetails();
			return new ResponseDTO<>(ResponseConstants.OK, ResponseConstants.IMPORT_SUCCESS_MESSAGE, employees);
				
		} catch(EmployeeServiceException e) {
			return new ResponseDTO<>(ResponseConstants.BAD_REQUEST, e.getMessage());
		}
	}
	
	public ResponseDTO<Integer> writeEmployeeToDB(String filePath) {
		
		if (filePath == null || filePath.trim().isEmpty()) {
			return new ResponseDTO<>(ResponseConstants.BAD_REQUEST, ResponseConstants.EMPTY_CSV_MESSAGE);
	    }

	    if (!filePath.toLowerCase().endsWith(".csv")) {
	        return new ResponseDTO<>(ResponseConstants.BAD_REQUEST, ResponseConstants.INVALID_CSV_MESSAGE);
	    }
	    
		HashMap<String, Integer> importstatus =null;
		
		try {
			importstatus = employeeService.importEmployeeToDB(filePath);
	
			int totalEmployeeData = importstatus.get("totalData");
			int successEmployeeEntries = importstatus.get("successData");
				
			if (totalEmployeeData == 0) {
				return new ResponseDTO<>(ResponseConstants.BAD_REQUEST, ResponseConstants.ERROR_CSV_MESSAGE);
			} else if(successEmployeeEntries == 0) {
				return new ResponseDTO<>(ResponseConstants.FULL_VALID_ERROR, ResponseConstants.FULL_VALID_ERROR_MESSAGE);
			} else if(successEmployeeEntries < totalEmployeeData) {
				return new ResponseDTO<>(ResponseConstants.PARTIAL_VALID_ERROR, ResponseConstants.PARTIAL_VALID_ERROR_MESSAGE, successEmployeeEntries);
			} else {
				return new ResponseDTO<>(ResponseConstants.PARTIAL_VALID_ERROR, ResponseConstants.PARTIAL_VALID_ERROR_MESSAGE, successEmployeeEntries);
			}
			
		} catch(EmployeeServiceException e) {
			return new ResponseDTO<>(ResponseConstants.BAD_REQUEST,e.getMessage());
		}
	}
	
	public ResponseDTO<Void> removeEmployeeFromDB(int id){
		
		try {
			
			employeeService.removeEmployee(id);
			return new ResponseDTO<>(ResponseConstants.OK,ResponseConstants.REMOVE_SUCCESS_MESSAGE);
			
		}catch(EmployeeServiceException e) {
			return new ResponseDTO<>(ResponseConstants.BAD_REQUEST,e.getMessage());
		}
		
	}
	
	public ResponseDTO<Void> updateExistingEmployee(String[] employee){
		try {
			
			employeeService.updateEmployee(employee);
			return new ResponseDTO<>(ResponseConstants.OK,ResponseConstants.UPDATE_SUCCESS_MESSAGE);
			
		} catch(EmployeeServiceException e) {
			return new ResponseDTO<>(ResponseConstants.BAD_REQUEST,e.getMessage());
		}
	}
	
	public ResponseDTO<EmployeeDTO> retrieveEmployeeByID(int id){
		try {
			EmployeeDTO employee = employeeService.getEmployeeByID(id);
			return new ResponseDTO<>(ResponseConstants.OK,ResponseConstants.UPDATE_SUCCESS_MESSAGE, employee);
		} catch(EmployeeServiceException e) {
			return new ResponseDTO<>(ResponseConstants.BAD_REQUEST,e.getMessage());
		}
	}
	
	public ResponseDTO<Void> addNewEmployee(String[] employee){
		try {
			
			employeeService.addEmployee(employee);
			return new ResponseDTO<>(ResponseConstants.OK,ResponseConstants.ADD_SUCCESS_MESSAGE);
			
		} catch(EmployeeServiceException e) {
			return new ResponseDTO<>(ResponseConstants.BAD_REQUEST,e.getMessage());
		}
	}
}
