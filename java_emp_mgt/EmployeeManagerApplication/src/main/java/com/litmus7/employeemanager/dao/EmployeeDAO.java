package com.litmus7.employeemanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.litmus7.employeemanager.constant.SQLConstants;
import com.litmus7.employeemanager.dto.EmployeeDTO;
import com.litmus7.employeemanager.util.DatabaseConnectionUtil;

public class EmployeeDAO {
	
	public int insertEmployee(List<EmployeeDTO> employee) {
		Integer id;
		String first_name, last_name, email, phone, department;
		double salary;
		Date join_date;
		int count = 0;
	
		try(Connection conn = DatabaseConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(SQLConstants.EMPLOYEE_INSERT);) {
		
			for(EmployeeDTO data : employee) {
				id = data.getID();
				first_name = data.getFirstName();
				last_name = data.getLastName();
				email = data.getEmail();
				phone = data.getPhone();
				department = data.getDepartment();
				salary = data.getSalary();
				join_date = data.getJoinDate();
				
			    ps.setInt(1, id);
		        ps.setString(2, first_name);
		        ps.setString(3, last_name);
		        ps.setString(4, email);
		        ps.setString(5, phone);
		        ps.setString(6, department);
		        ps.setDouble(7, salary);
		        ps.setDate(8, join_date);
		        ps.addBatch();
		        
		        count++;
			}    
			
			ps.executeBatch();
            ps.close();
            
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return count;	
	}
}
