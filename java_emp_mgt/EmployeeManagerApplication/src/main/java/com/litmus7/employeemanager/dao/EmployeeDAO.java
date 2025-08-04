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
	
	public boolean getEmployeeByID(int id) {

		boolean status = false;
		
		try(Connection conn = DatabaseConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(SQLConstants.CHECK_EMPLOYEE_BY_ID)){
			ps.setInt(1, id);
			
			try(ResultSet rs = ps.executeQuery()){
				if(rs.next()) {
					status = true;
				}
			}
			
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return status;
	}
	
	public List<EmployeeDTO> selectAllEmployee() {
		
		List<EmployeeDTO> allEmployees = new ArrayList<>();
		
		try(Connection conn = DatabaseConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(SQLConstants.ALL_EMPLOYEE_SELECT)){
			
			try(ResultSet rs = ps.executeQuery()){
				
				while(rs.next()) {
					EmployeeDTO employee = new EmployeeDTO();
					employee.setID(rs.getInt("emp_id"));
					employee.setFirstName(rs.getString("first_name"));
					employee.setLastName(rs.getString("last_name"));
					employee.setEmail(rs.getString("email"));
					employee.setPhone(rs.getString("phone"));
					employee.setDepartment(rs.getString("department"));
					employee.setSalary(rs.getDouble("salary"));
					employee.setJoinDate(rs.getDate("join_date"));
					
					allEmployees.add(employee);
				}
			}
			
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return allEmployees;
		
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
				PreparedStatement ps = conn.prepareStatement(SQLConstants.EMPLOYEE_INSERT);) {
		
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
