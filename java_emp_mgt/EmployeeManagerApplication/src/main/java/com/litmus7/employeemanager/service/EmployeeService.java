package com.litmus7.employeemanager.service;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.litmus7.employeemanager.dao.EmployeeDAO;
import com.litmus7.employeemanager.dto.EmployeeDTO;
import com.litmus7.employeemanager.exception.EmployeeDAOException;
import com.litmus7.employeemanager.exception.EmployeeServiceException;
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
	
	
	
	public List<EmployeeDTO> exportEmployeeDetails() throws EmployeeServiceException{
		
		List<EmployeeDTO> employeesList = null;
		
		try {
			employeesList = employeeDao.getEmployees();
			
			if(employeesList == null || employeesList.isEmpty()) {
				LOGGER.warning("The table contains no data to be retrieved.");
				throw new EmployeeServiceException("The table contains no data to be retrieved.");
			}
			
			return employeesList;
			
		} catch(EmployeeDAOException e) {
			LOGGER.severe("Error while exporting data : " + e.getMessage());
			throw new EmployeeServiceException(e.getMessage(), e);
		}
		
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
	
	public HashMap<String, Integer> importEmployeeToDB(String filepath) throws EmployeeServiceException{
		
		HashMap<String, Integer> result = new HashMap<>();
		List<String[]> rawEmployees = null;
		
		int dataIndex = 0;
		int totalData = 0;
		int successData = 0;

	    result.put("totalData",totalData);
	    result.put("successData", successData);
		
		try {
			rawEmployees = CSVUtil.readCSV(filepath);
		} catch(IOException e) {
			LOGGER.severe("Failed to read CSV file : " + e.getMessage());
			throw new EmployeeServiceException(e.getMessage(), e);
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
			result.put("successData", successData);
			
			return result;
			
		} catch(Exception e) {
			LOGGER.severe("Row " + dataIndex + " : Something went wrong : " + e.getMessage());
			throw new EmployeeServiceException(e.getMessage(), e);
		} 
	}
	
	public void removeEmployee(int id) throws EmployeeServiceException {
		try {

			int deleteStatus = employeeDao.deleteEmployee(id);
			
			if(deleteStatus == 0) {
				LOGGER.warning("No employee was found with the ID " + id);
				throw new EmployeeServiceException("No employee was found with the ID " + id);
			}
			
		}catch(EmployeeDAOException e) {
			LOGGER.severe("Error while removing employee with ID " + id + " : " + e.getMessage());
			throw new EmployeeServiceException(e.getMessage(), e);
		}
		
	}
	
	public void updateEmployee(String[] rawEmployee) throws EmployeeServiceException {
		
		if(rawEmployee.length != 8) {
			LOGGER.warning("Employee with ID " + rawEmployee[0] + " has insufficient data found.");
			throw new EmployeeServiceException("Updation of Employee with ID " + rawEmployee[0] + " has failed validation.");
		}
		
		if(! ValidationsUtil.validateEmployee(rawEmployee)) {
			LOGGER.warning("Employee with ID " + rawEmployee[0] + " has failed validation.");
			throw new EmployeeServiceException("Updation of Employee with ID " + rawEmployee[0] + " has failed validation.");
		}
		
		EmployeeDTO employee = convertToEmployeeObject(rawEmployee);
		
		try {
			int updateStatus = employeeDao.updateEmployee(employee);
			
			if(updateStatus == 0) {
				LOGGER.warning("No employee was found with the ID " + employee.getID());
				throw new EmployeeServiceException("No employee was found with the ID " + employee.getID());
			}
			
		} catch (EmployeeDAOException e) {
			LOGGER.severe("Error while updating employee with ID " + rawEmployee[0] + " : " + e.getMessage());
			throw new EmployeeServiceException(e.getMessage(), e);
		}
	}
	
	public EmployeeDTO getEmployeeByID(int id) throws EmployeeServiceException {
		try {
			
			EmployeeDTO employee = employeeDao.getEmployeeByID(id);
			
			if(employee == null) {
				LOGGER.warning( "No employee with the ID " + id + ".");
				throw new EmployeeServiceException("No employee with the ID " + id + ".");
			}
			
			return employee;
			
		}catch(EmployeeDAOException e) {
			LOGGER.severe("Error while retrieving employee : " + e.getMessage());
			throw new EmployeeServiceException(e.getMessage(), e);
		}
	}
	
	public void addEmployee(String[] rawEmployee) throws EmployeeServiceException {
		
		try {
			
			if(rawEmployee.length != 8) {
				LOGGER.warning("Employee with ID " + rawEmployee[0] + " has insufficient data found.");
				throw new EmployeeServiceException("Insertion of Employee with ID " + rawEmployee[0] + " has failed validation.");
			}
			
			if(! ValidationsUtil.validateEmployee(rawEmployee)) {
				LOGGER.warning("Employee with ID " + rawEmployee[0] + " has failed validation.");
				throw new EmployeeServiceException("Insertion of Employee with ID " + rawEmployee[0] + " has failed validation.");
			}
			
			if(employeeDao.getEmployeeByID(Integer.parseInt(rawEmployee[0])) != null) {
				LOGGER.warning("Employee with ID " + rawEmployee[0] + " has failed validation.");
				throw new EmployeeServiceException("Insertion of Employee with ID " + rawEmployee[0] + " has failed validation.");
			}
			
			EmployeeDTO employee = convertToEmployeeObject(rawEmployee);
			
			employeeDao.saveEmployee(employee);
			
		} catch (EmployeeDAOException e) {
			LOGGER.severe("Error while updating employee with ID " + rawEmployee[0] + " : " + e.getMessage());
			throw new EmployeeServiceException(e.getMessage(), e);
		}
	}
	
	public HashMap<String, Integer> addEmployeeInBatch(List<String[]> rawEmployees) throws EmployeeServiceException {
		
		List<String> ids = new ArrayList<>();
		HashMap<String, Integer> result = new HashMap<>();
		List<EmployeeDTO> employees = null;
		int totalData = rawEmployees.size();
		int successData = 0;
		
		result.put("successData", 0);
		result.put("totalData", 0);
	
		try {
			
			employees = new ArrayList<>();
			
			for(String[] rawEmployee : rawEmployees) {
				if(rawEmployee.length != 8) {
					LOGGER.warning("Employee with ID " + rawEmployee[0] + " has insufficient data found.");
					continue;
				}
				
				if(! ValidationsUtil.validateEmployee(rawEmployee)) {
					LOGGER.warning("Employee with ID " + rawEmployee[0] + " has failed validation.");
					continue;
				}
				
				if(employeeDao.getEmployeeByID(Integer.parseInt(rawEmployee[0])) != null) {
					LOGGER.warning("Employee with ID " + rawEmployee[0] + " already exists in the table.");
					continue;
				}
				
				if(ids.contains(rawEmployee[0])) {
					LOGGER.warning("Employee with ID " + rawEmployee[0] + " has occured twice in the input data.");
					continue;
				}
				
				ids.add(rawEmployee[0]);
				employees.add(convertToEmployeeObject(rawEmployee));
			}
			
			successData = employeeDao.addEmployeesInBatch(employees);
			
			result.put("successData", successData);
			result.put("totalData", totalData);
			
			return result;
			
		} catch (EmployeeDAOException e) {
			LOGGER.severe("Error while adding employee in batch : " + e.getMessage());
			throw new EmployeeServiceException(e.getMessage(), e);
		}
	}
	
	public HashMap<String, Integer> transferEmployeesToDepartment(List<Integer> employeeIds, String newDepartment) throws EmployeeServiceException {
		
		HashMap<String, Integer> result = new HashMap<>();
		
		if(employeeIds.isEmpty() || employeeIds == null) {
			LOGGER.severe("The given list doesnt contain any values.");
			throw new EmployeeServiceException("The given list doesnt contain any values.");
		}
		if(newDepartment.isBlank() || newDepartment == null) {
			LOGGER.severe("There is no department specified for the transfering.");
			throw new EmployeeServiceException("There is no department specified for the transfering.");
		}
		
		int totalIds = employeeIds.size();
		
		try {
			
			int transferCount = employeeDao.transferEmployeesToDepartment(employeeIds, newDepartment);
			
			result.put("totalIds", totalIds);
			result.put("transferCount", transferCount);
			
			return result;
			
		} catch (EmployeeDAOException e) {
			LOGGER.severe("Error while changing the department of employees : " + e.getMessage());
			throw new EmployeeServiceException(e.getMessage(), e);
		}
	}
}
