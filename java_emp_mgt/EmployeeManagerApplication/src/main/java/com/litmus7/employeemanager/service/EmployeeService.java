package com.litmus7.employeemanager.service;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.litmus7.employeemanager.dao.EmployeeDAO;
import com.litmus7.employeemanager.dto.EmployeeDTO;
import com.litmus7.employeemanager.util.CSVUtil;
import com.litmus7.employeemanager.util.ValidationsUtil;

public class EmployeeService {
	private EmployeeDAO employeeDao = new EmployeeDAO();
	
	private static final Logger LOGGER = Logger.getLogger(EmployeeService.class.getName());
	
	static {
		try {
			LogManager.getLogManager().reset();
			
			FileHandler fileHandler = new FileHandler("logs/employee-import-error.log",true);
			fileHandler.setFormatter(new SimpleFormatter());
			LOGGER.addHandler(fileHandler);
			LOGGER.setLevel(Level.ALL);
			
		} catch(IOException e) {
			System.err.println("Failed to set up logger: " + e.getMessage());
		}
	}
	
	
	
	public List<EmployeeDTO> exportEmployeeDetails(){
		List<EmployeeDTO> employeesList = employeeDao.getEmployees();
		return employeesList;
	}
	
	
	public static EmployeeDTO convertToEmployeeObject(String[] rawEmployee) {
		EmployeeDTO employee = new EmployeeDTO();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(rawEmployee[7], formatter);
			
		employee.setID(Integer.parseInt(rawEmployee[0].trim()));
		employee.setFirstName(rawEmployee[1].trim());
		employee.setLastName(rawEmployee[2].trim());
		employee.setEmail(rawEmployee[3].trim());
		employee.setPhone(rawEmployee[4].trim());
		employee.setDepartment(rawEmployee[5].trim());
		employee.setSalary(Double.parseDouble(rawEmployee[6].trim()));
		employee.setJoinDate(Date.valueOf(date));

		return employee;
	}
	
	public HashMap<String, Integer> importEmployeeToDB(String filepath){
		
		HashMap<String, Integer> result = new HashMap<>();
		List<String[]> rawEmployees = null;
		
		int dataIndex = 0;
		int totalData = 0;
		int successData = 0;

	    result.put("totalData",totalData);
	    result.put("successData", successData);
		
		try {
			rawEmployees = CSVUtil.readCSV(filepath);
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Failed to read CSV file : " + e);
			return result;
		}
		
		result.put("totalData", rawEmployees.size());
		
		try {
			
			for(String[] rawEmployee : rawEmployees) {
				dataIndex++;
				
				if(rawEmployee.length != 8) {
					LOGGER.warning("Row " + dataIndex + " : Insufficient data found.");
					continue;
				}
				
				if(! ValidationsUtil.validateEmployee(rawEmployee)) {
					LOGGER.warning("Row " + dataIndex + " : Validation failed.");
					continue;
				}
				
				if(employeeDao.getEmployeeByID(Integer.parseInt(rawEmployee[0])) != null) {
					LOGGER.warning("Row " + dataIndex + " : Duplicate entry for employee ID " + rawEmployee[0]);
					continue;
				}
				
				if(employeeDao.saveEmployee(convertToEmployeeObject(rawEmployee))){
					successData++;
				} else {
					LOGGER.warning("Row " + dataIndex + " : Failed to insert the data.");
				}
				
			}
			
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Row " + dataIndex + " : Something went wrong : " + e.getMessage());
		}
		
		result.put("successData", successData);
		
		return result; 
	}
	
}
