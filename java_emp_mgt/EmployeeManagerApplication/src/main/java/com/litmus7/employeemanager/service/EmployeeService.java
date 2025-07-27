package com.litmus7.employeemanager.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.litmus7.employeemanager.dao.EmployeeDAO;
import com.litmus7.employeemanager.dto.EmployeeDTO;
import com.litmus7.employeemanager.dto.EmployeeValidDTO;
import com.litmus7.employeemanager.util.CSVUtil;
import com.litmus7.employeemanager.util.ValidationUtil;

public class EmployeeService {
	
	public EmployeeValidDTO ValidateEmployeeDetails(List<String[]> employee_data) {
		List<String[]> validEmployeeData = new ArrayList<>();
		List<String> duplicate = new ArrayList<>();
		
		for(String[] data : employee_data) {
			String id = data[0].trim();
			String firstName = data[1].trim();
            String lastName = data[2].trim();
            String email = data[3].trim();
            String phone = data[4].trim();
            String department = data[5].trim();
            String salary = data[6].trim();
            String date = data[7].trim();

            if ( ! duplicate.contains(data[0]) && ValidationUtil.isValidID(id)  && ValidationUtil.isValidName(firstName) && ValidationUtil.isValidName(lastName) && ValidationUtil.isValidEmail(email) && ValidationUtil.isValidPhone(phone) && ValidationUtil.isValidDepartment(department) && ValidationUtil.isValidSalary(salary) && ValidationUtil.isValidJoinDate(date)) {
            	
            	duplicate.add(data[0]);
            	validEmployeeData.add(data);
            }
		}
		
        return new EmployeeValidDTO(validEmployeeData);
	}
		
	
	public List<EmployeeDTO> convertToEmployeeObject(List<String[]> csvdata) {
		List<EmployeeDTO> employeeList = new ArrayList<>();
		
		for(String[] data : csvdata) {
			EmployeeDTO employee = new EmployeeDTO();
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
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
	
	public EmployeeValidDTO writeEmployeeToDB(String filepath) {
		CSVUtil csv = new CSVUtil();
		
		List<String[]> employeeCSVData = csv.readCSV(filepath);
		
		EmployeeValidDTO validEmployeeEntry = this.ValidateEmployeeDetails(employeeCSVData);
		
		List<EmployeeDTO> valid_employee_data =this.convertToEmployeeObject(validEmployeeEntry.getData());	
		
		EmployeeDAO employeeDAO = new EmployeeDAO(); 
		
		int validEntryCount = employeeDAO.insertEmployee(valid_employee_data);
		
		if (validEntryCount != employeeCSVData.size()) {
			return new EmployeeValidDTO(false, validEntryCount);
		}
		
		return new EmployeeValidDTO(true, validEntryCount);
		
	}
	
}
