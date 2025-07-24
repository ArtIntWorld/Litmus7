package com.litmus7.employeemanager.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.litmus7.employeemanager.constant.ResponseConstants;
import com.litmus7.employeemanager.dao.EmployeeDAO;
import com.litmus7.employeemanager.dto.EmployeeDTO;
import com.litmus7.employeemanager.dto.EmployeeServiceDTO;
import com.litmus7.employeemanager.util.CSVUtil;
import com.litmus7.employeemanager.util.ValidationUtil;

public class EmployeeService {
	
	public EmployeeServiceDTO ValidateEmployeeDetails(List<String[]> employee_data) {
		int status = 0;
		List<String[]> validEmployeeData = new ArrayList<>();
		List<String> errorMessages = new ArrayList<>();
		List<String> duplicate = new ArrayList<>();
		
		for(String[] data : employee_data) {
			
			String firstName = data[1].trim();
            String lastName = data[2].trim();
            String email = data[3].trim();
            String phone = data[4].trim();
            String department = data[5].trim();
            String salary = data[6].trim();
            String date = data[7].trim();

            if (ValidationUtil.isValidName(firstName) && ValidationUtil.isValidName(lastName) && ValidationUtil.isValidEmail(email) && ValidationUtil.isValidPhone(phone) && ValidationUtil.isValidDepartment(department) && ValidationUtil.isValidSalary(salary) && ValidationUtil.isValidJoinDate(date)) {
            	
            	errorMessages.add("ID " + data[0] + ": Invalid data entry.");
                status = ResponseConstants.VALIDATION_FAILED;  
            }else if (duplicate.contains(data[0])) {
            	errorMessages.add("ID " + data[0] + ": ID already exists.");
            	status = ResponseConstants.VALIDATION_FAILED; 
            }
            else {
            	duplicate.add(data[0]);
            	
            	validEmployeeData.add(data);
            }
		}
		
		if(status == ResponseConstants.VALIDATION_FAILED) {
			
        	return new EmployeeServiceDTO(0,status,validEmployeeData,errorMessages);
        }
		
        return new EmployeeServiceDTO(0,status,validEmployeeData,null);
	}
	
	public List<EmployeeDTO> convertToEmployeeObject(List<String[]> csvdata) {
		List<EmployeeDTO> employeeList = new ArrayList<>();
		
		for(String[] data : csvdata) {
			EmployeeDTO employee = new EmployeeDTO();
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(data[7], formatter);
			
			employee.setID(Integer.parseInt(data[0].trim()));
			employee.setFirstName(data[1].trim());
			employee.setLastName(data[2].trim());
			employee.setEmail(data[3].trim());
			employee.setPhone(data[4].trim());
			employee.setDepartment(data[5].trim());
			employee.setSalary(Double.parseDouble(data[6].trim()));
			employee.setJoinDate(Date.valueOf(date));
			
			employeeList.add(employee);
		}	
		return employeeList;
	}
	
	public EmployeeServiceDTO writeEmployeeToDB(String filepath) {
		CSVUtil csv = new CSVUtil();
		
		if( ! ValidationUtil.validateCSVType(filepath)) {
			
			return new EmployeeServiceDTO(ResponseConstants.FILE_NOT_FOUND,0); 
		}
		
		List<String[]> csv_employee_data = csv.readCSV(filepath);
		
		for (String[] row : csv_employee_data) {
		    System.out.println(Arrays.toString(row));
		}

		
		
		if(csv_employee_data == null) {
			
			return new EmployeeServiceDTO(ResponseConstants.FILE_NOT_FOUND,0); 
		}
		
		EmployeeServiceDTO validEmployeeEntry = this.ValidateEmployeeDetails(csv_employee_data);
		
		List<EmployeeDTO> valid_employee_data =this.convertToEmployeeObject(validEmployeeEntry.getEmployeeData());	
		
		EmployeeDAO employeeDAO = new EmployeeDAO(); 
		
		int validEntryCount = employeeDAO.insertEmployee(valid_employee_data);
		
		
		validEmployeeEntry.setEmployeeCount(validEntryCount);
		
		return validEmployeeEntry;
	}
	
}
