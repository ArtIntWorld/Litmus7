package com.litmus7.employeemanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.litmus7.employeemanager.constant.SQLConstants;
import com.litmus7.employeemanager.dto.EmployeeDTO;
import com.litmus7.employeemanager.util.DatabaseConnectionUtil;

public class EmployeeDAO {

	public EmployeeDTO getEmployeeByID(int id) {
		
		EmployeeDTO employee = null;
		
		try(Connection conn = DatabaseConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(SQLConstants.GET_EMPLOYEE_BY_ID)){
			
			ps.setInt(1, id);
			
			try(ResultSet rs = ps.executeQuery()){
				if(rs.next()) {	
					employee = new EmployeeDTO();
					
					employee.setID(rs.getInt(1));
					employee.setFirstName(rs.getString(2));
					employee.setLastName(rs.getString(3));
					employee.setEmail(rs.getString(4));
					employee.setPhone(rs.getString(5));
					employee.setDepartment(rs.getString(6));
					employee.setSalary(rs.getDouble(7));
					employee.setJoinDate(rs.getDate(8));
				}
				
			}
			
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
		System.out.println(employee);
		return employee;
	}
	
	public List<EmployeeDTO> getEmployees() {
		
		List<EmployeeDTO> employees = new ArrayList<>();
		
		try(Connection conn = DatabaseConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(SQLConstants.GET_EMPLOYEES)){
			
			try(ResultSet rs = ps.executeQuery()){
				
				while(rs.next()) {
					EmployeeDTO employee = new EmployeeDTO();
					employee.setID(rs.getInt(1));
					employee.setFirstName(rs.getString(2));
					employee.setLastName(rs.getString(3));
					employee.setEmail(rs.getString(4));
					employee.setPhone(rs.getString(5));
					employee.setDepartment(rs.getString(6));
					employee.setSalary(rs.getDouble(7));
					employee.setJoinDate(rs.getDate(8));
					
					employees.add(employee);
				}
			}
			
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return employees;
		
	}
	
	public boolean saveEmployee(EmployeeDTO employee) {
		
		Integer id = employee.getID();
		String first_name = employee.getFirstName();
		String last_name = employee.getLastName();
		String email = employee.getEmail();
		String phone = employee.getPhone();
		String department = employee.getDepartment();
		double salary = employee.getSalary();
		Date join_date = employee.getJoinDate();
		
		try(Connection conn = DatabaseConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(SQLConstants.ADD_EMPLOYEE);) {
		
		    ps.setInt(1, id);
	        ps.setString(2, first_name);
	        ps.setString(3, last_name);
	        ps.setString(4, email);
	        ps.setString(5, phone);
	        ps.setString(6, department);
	        ps.setDouble(7, salary);
	        ps.setDate(8, join_date);
	        
	        int rowsInserted = ps.executeUpdate();
	        return rowsInserted > 0;

		} catch(SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}


