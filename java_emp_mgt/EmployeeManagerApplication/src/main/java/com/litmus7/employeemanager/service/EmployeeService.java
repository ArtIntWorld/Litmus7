package com.litmus7.employeemanager.service;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.litmus7.employeemanager.dao.EmployeeDAO;
import com.litmus7.employeemanager.dto.EmployeeDTO;
import com.litmus7.employeemanager.exception.EmployeeDAOException;
import com.litmus7.employeemanager.exception.EmployeeServiceException;
import com.litmus7.employeemanager.util.CSVUtil;
import com.litmus7.employeemanager.util.ValidationsUtil;

public class EmployeeService {
	private EmployeeDAO employeeDao = new EmployeeDAO();
	
	private static final Logger logger = LogManager.getLogger(EmployeeService.class);
	
	public List<EmployeeDTO> exportEmployeeDetails() throws EmployeeServiceException{
		
		logger.trace("Entering exportEmployeeDetails().");
		
		List<EmployeeDTO> employeesList = null;
		
		try {
			employeesList = employeeDao.getEmployees();
			
			if(employeesList == null || employeesList.isEmpty()) {
				logger.error("The table contains no data to be retrieved.\n");
				throw new EmployeeServiceException("The table contains no data to be retrieved.");
			}
			
			logger.trace("Exiting exportEmployeeDetails() with {} employees.", employeesList.size());
			return employeesList;
			
		} catch(EmployeeDAOException e) {
			logger.error("Error in exportEmployeeDetails() : {}.", e.getMessage());
			throw new EmployeeServiceException(e.getErrorCode(), e);
		}
		
	}
	
	
	public static EmployeeDTO convertToEmployeeObject(String[] rawEmployee) {
		
		logger.trace("Entering convertToEmployeeObject().");
		logger.debug("Raw data of the employee are id: {}, first name: {}, last name: {}, email: {}, phone: {}, department: {}, salary: {}, join date: {}", rawEmployee[0], rawEmployee[1], rawEmployee[2], rawEmployee[3], rawEmployee[4], rawEmployee[5], rawEmployee[6], rawEmployee[7]);
		
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


		logger.trace("Exiting convertToEmployeeObject().");
		return employee;
		
	}
	
	public HashMap<String, Integer> importEmployeeToDB(String filepath) throws EmployeeServiceException{
		
		logger.trace("Entering importEmployeeToDB() with filepath {}", filepath);
		
		
		HashMap<String, Integer> result = new HashMap<>();
		List<String[]> rawEmployees = null;
		int totalData = 0;
		int successData = 0;

	    result.put("totalData",totalData);
	    result.put("successData", successData);
		
		try {
			rawEmployees = CSVUtil.readCSV(filepath);
		} catch(IOException e) {
			logger.error("Error while reading the file : {}.", e.getMessage());
			throw new EmployeeServiceException(121, e);
		}
		
		result.put("totalData", rawEmployees.size());
		
		try {
			
			for(String[] rawEmployee : rawEmployees) {
				
				if(rawEmployee.length != 8) {
					logger.warn("Employee with id {} doesn't contain enough data.", rawEmployee[0]);
					continue;
				}
				
				if(! ValidationsUtil.validateEmployee(rawEmployee)) {
					logger.warn("Employee with id {} contains some validation mistake.", rawEmployee[0]);
					continue;
				}
				
				if(employeeDao.getEmployeeByID(Integer.parseInt(rawEmployee[0])) != null) {
					logger.warn("The employee already exists with the id {} in the database.", rawEmployee[0]);
					continue;
				}
				
				if(employeeDao.saveEmployee(convertToEmployeeObject(rawEmployee))){
					successData++;
				} else {
				}
				
			}
			result.put("successData", successData);
			
			logger.trace("Exiting importEmployeeToDB() with {} successful entries.", successData);
			
			return result;
			
		} catch(EmployeeDAOException e) {
			logger.error("Error in importEmployeeToDB() : {}", e.getMessage());
			throw new EmployeeServiceException(e.getErrorCode(), e);
		} 
	}
	
	public void removeEmployee(int id) throws EmployeeServiceException {
		
		logger.trace("Entering removeEmployee with employee id {}.", id);
		
		try {

			int deleteStatus = employeeDao.deleteEmployee(id);
			
			if(deleteStatus == 0) {
				logger.error("No employee was found with the ID " + id);
				throw new EmployeeServiceException(106);
			}
			
			logger.trace("Exiting removeEmployee() with {} employee deleted.", deleteStatus);
			
		} catch(EmployeeDAOException e) {
			logger.error("Error in removeEmployee() : {}.", e.getMessage());
			throw new EmployeeServiceException(e.getErrorCode(), e);
		}
		
	}
	
	public void updateEmployee(String[] rawEmployee) throws EmployeeServiceException {
		
		logger.trace("Entering updateEmployee().");
		logger.debug("Raw data of the employee are id: {}, first name: {}, last name: {}, email: {}, phone: {}, department: {}, salary: {}, join date: {}", rawEmployee[0], rawEmployee[1], rawEmployee[2], rawEmployee[3], rawEmployee[4], rawEmployee[5], rawEmployee[6], rawEmployee[7]);
		
		if(rawEmployee.length != 8) {
			logger.error("Given employee doesn't contain enough data.");
			throw new EmployeeServiceException(100);
		}
		
		if(! ValidationsUtil.validateEmployee(rawEmployee)) {
			logger.error("Given employee contains some validation mistake.");
			throw new EmployeeServiceException(101);
		}
		
		EmployeeDTO employee = convertToEmployeeObject(rawEmployee);
		
		try {
			int updateStatus = employeeDao.updateEmployee(employee);
			
			if(updateStatus == 0) {
				logger.error("No employee was found with the ID " + employee.getID());
				throw new EmployeeServiceException(106);
			}
			
			logger.trace("Exiting updateEmployee() after successful update.");
			
		} catch (EmployeeDAOException e) {
			logger.error("Error in updateEmployee() : {}.", e.getMessage());
			throw new EmployeeServiceException(e.getErrorCode(), e);
		}
	}
	
	public EmployeeDTO getEmployeeByID(int id) throws EmployeeServiceException {
		
		logger.trace("Entering getEmployeeByID() with employee id {}.", id);
		try {
			
			EmployeeDTO employee = employeeDao.getEmployeeByID(id);
			
			if(employee == null) {
				logger.error("No employee with the ID " + id + ".");
				throw new EmployeeServiceException(106);
			}
			
			logger.debug("Retrieved details of employee of id {} : first name: {}, last name: {}, email: {}, phone: {}, department: {}, salary: {}, join date: {}.", id, employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getPhone(), employee.getDepartment(), employee.getSalary(), employee.getJoinDate());
			logger.trace("Exiting getEmployeeByID() after successfull retrieval");
			
			return employee;
			
		}catch(EmployeeDAOException e) {
			logger.error("Error in getEmployeeByID() : {}.", e.getMessage());
			throw new EmployeeServiceException(e.getErrorCode(), e);
		}
	}
	
	public void addEmployee(String[] rawEmployee) throws EmployeeServiceException {
		
		logger.trace("Entering addEmployee().");
		logger.debug("Raw data of the employee are id: {}, first name: {}, last name: {}, email: {}, phone: {}, department: {}, salary: {}, join date: {}", rawEmployee[0], rawEmployee[1], rawEmployee[2], rawEmployee[3], rawEmployee[4], rawEmployee[5], rawEmployee[6], rawEmployee[7]);
		
		try {
			
			if(rawEmployee.length != 8) {
				logger.error("Given employee doesn't contain enough data.");
				throw new EmployeeServiceException(100);
			}
			
			if(! ValidationsUtil.validateEmployee(rawEmployee)) {
				logger.error("Given employee contains some validation mistake.");
				throw new EmployeeServiceException(101);
			}
			
			if(employeeDao.getEmployeeByID(Integer.parseInt(rawEmployee[0])) != null) {
				logger.error("The employee already exists with the id {}.", rawEmployee[0]);
				throw new EmployeeServiceException(110);
			}
			
			EmployeeDTO employee = convertToEmployeeObject(rawEmployee);
			
			employeeDao.saveEmployee(employee);
			
			logger.trace("Exiting addEmployee() after successful save.");
			
		} catch (EmployeeDAOException e) {
			logger.error("Error in saveEmployee() : {}.", e.getMessage());
			throw new EmployeeServiceException(e.getErrorCode(), e);
		}
	}
	
	public HashMap<String, Integer> addEmployeeInBatch(List<String[]> rawEmployees) throws EmployeeServiceException {
		
		logger.trace("Entering addEmployeeInBatch() with {} employee details.", rawEmployees.size());
		
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
					logger.warn("Employee with id {} doesn't contain enough data.", rawEmployee[0]);
					continue;
				}
				
				if(! ValidationsUtil.validateEmployee(rawEmployee)) {
					logger.warn("Employee with id {} contains some validation mistake.", rawEmployee[0]);
					continue;
				}
				
				if(employeeDao.getEmployeeByID(Integer.parseInt(rawEmployee[0])) != null) {
					logger.warn("The employee already exists with the id {} in the database.", rawEmployee[0]);
					continue;
				}
				
				if(ids.contains(rawEmployee[0])) {
					logger.warn("The employee already exists with the id {} in the list already.", rawEmployee[0]);
					continue;
				}
				
				ids.add(rawEmployee[0]);
				employees.add(convertToEmployeeObject(rawEmployee));
			}
			
			logger.info("Converted {} valid employee data.", employees.size());
			
			successData = employeeDao.addEmployeesInBatch(employees);
			
			result.put("successData", successData);
			result.put("totalData", totalData);
			
			logger.trace("Exiting addEmployeeInBatch() with {} successful entries.", successData);
			return result;
			
		} catch (EmployeeDAOException e) {
			logger.error("Error in addEmployeeInBatch() : {}.", e.getMessage());
			throw new EmployeeServiceException(e.getErrorCode(), e);
		}
	}
	
	public HashMap<String, Integer> transferEmployeesToDepartment(List<Integer> employeeIds, String newDepartment) throws EmployeeServiceException {
		
		logger.trace("Entering transferEmployeesToDepartment() with {} employee Ids and {} as new department.",employeeIds.size(), newDepartment);
		
		HashMap<String, Integer> result = new HashMap<>();
		
		if(employeeIds.isEmpty() || employeeIds == null) {
			logger.error("The given list doesnt contain any values.");
			throw new EmployeeServiceException(100);
		}
		if(newDepartment.isBlank() || newDepartment == null) {
			logger.error("There is no department specified for the transfering.");
			throw new EmployeeServiceException(100);
		}
		
		int totalIds = employeeIds.size();
		
		try {
			
			int transferCount = employeeDao.transferEmployeesToDepartment(employeeIds, newDepartment);
			
			result.put("totalIds", totalIds);
			result.put("transferCount", transferCount);	
			
			logger.trace("Exiting transferEmployeesToDepartment() with {} successful updates.", transferCount);
			return result;
			
		} catch (EmployeeDAOException e) {
			logger.error("Error in transferEmployeesToDepartment() : {}", e.getMessage());
			throw new EmployeeServiceException(e.getErrorCode(), e);
		}
	}
}
