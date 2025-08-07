package com.litmus7.employeemanager.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.litmus7.employeemanager.constant.SQLConstants;
import com.litmus7.employeemanager.dto.EmployeeDTO;
import com.litmus7.employeemanager.exception.EmployeeDAOException;
import com.litmus7.employeemanager.service.EmployeeService;
import com.litmus7.employeemanager.util.DatabaseConnectionUtil;

public class EmployeeDAO {
	
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

	public EmployeeDTO getEmployeeByID(int id) throws EmployeeDAOException {
		
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
			return employee;
			
		} catch(SQLException e){
			LOGGER.log(Level.SEVERE, "Error at while selecting data by ID from database : " + e.getMessage());
			throw new EmployeeDAOException(e.getMessage(), e);
		}
		
	}
	
	public List<EmployeeDTO> getEmployees() throws EmployeeDAOException {
		
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
			return employees;
			
		} catch(SQLException e) {
			LOGGER.log(Level.SEVERE, "Error while selecting data from database : " + e.getMessage());
			throw new EmployeeDAOException(e.getMessage(), e);
		}
		
		
		
	}
	
	public boolean saveEmployee(EmployeeDTO employee) throws EmployeeDAOException {
		
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
			LOGGER.log(Level.SEVERE, "Error while saving data into database : " + e.getMessage());
			throw new EmployeeDAOException(e.getMessage(), e);
		}
	}
	
	public int deleteEmployee(int id) throws EmployeeDAOException {
		
		try(Connection conn = DatabaseConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(SQLConstants.DELETE_EMPLOYEE)){
			
			ps.setInt(1, id);
			int rowsAffected = ps.executeUpdate();
			return rowsAffected;
			
		}catch(SQLException e) {
			LOGGER.log(Level.SEVERE, "Error while deleting data from database : " + e.getMessage());
			throw new EmployeeDAOException(e.getMessage(), e);
		}
	}
	
	public int updateEmployee(EmployeeDTO employee) throws EmployeeDAOException {
		
		try(Connection conn = DatabaseConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(SQLConstants.UPDATE_EMPLOYEE)){
			
			ps.setInt(8, employee.getID());
			ps.setString(1, employee.getFirstName());
			ps.setString(2, employee.getLastName());
			ps.setString(3, employee.getEmail());
			ps.setString(4, employee.getPhone());
			ps.setString(5, employee.getDepartment());
			ps.setDouble(6, employee.getSalary());
			ps.setDate(7, employee.getJoinDate());
			
			int rowsAffected = ps.executeUpdate();
			return rowsAffected;
			
		}catch(SQLException e) {
			LOGGER.log(Level.SEVERE, "Error while updating data into database : " + e.getMessage());
			throw new EmployeeDAOException(e.getMessage(), e);
		}
	}
	
	public int addEmployeesInBatch(List<EmployeeDTO> employees) throws EmployeeDAOException {
		int rowsAdded = 0;
		
		try (Connection conn = DatabaseConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(SQLConstants.ADD_EMPLOYEE);){
			
			for(EmployeeDTO employee : employees) {
				ps.setInt(1, employee.getID());
		        ps.setString(2, employee.getFirstName());
		        ps.setString(3, employee.getLastName());
		        ps.setString(4, employee.getEmail());
		        ps.setString(5, employee.getPhone());
		        ps.setString(6, employee.getDepartment());
		        ps.setDouble(7, employee.getSalary());
		        ps.setDate(8, employee.getJoinDate());
		        
		        ps.addBatch();
		        rowsAdded++;
			}
			ps.executeBatch();
			
			return rowsAdded;
			
		} catch(SQLException e) {
			LOGGER.log(Level.SEVERE, "Error while adding data into database : " + e.getMessage());
			throw new EmployeeDAOException(e.getMessage(), e);
		}
	}
	
	public int transferEmployeesToDepartment(List<Integer> employeeIds, String newDepartment) throws EmployeeDAOException{
		
		int rowsAffected = 0;
		
		try(Connection conn = DatabaseConnectionUtil.getConnection()){
			
			conn.setAutoCommit(false);
			
			try (PreparedStatement ps = conn.prepareStatement(SQLConstants.TRANSFER_TO_DEPARTMENT)){
				
				for(int employeeId : employeeIds)
				{				
					ps.setString(1, newDepartment);
					ps.setInt(2, employeeId);
					ps.addBatch();
				}
				int[] results = ps.executeBatch();
				
				for(int result : results) {
					if(result > 0) {
						rowsAffected++;
					}
				}
				
				conn.commit();
				return rowsAffected;
				
			}catch(SQLException e) {
				conn.rollback();
				LOGGER.log(Level.SEVERE, "Error while updating with department " + newDepartment + " : " + e.getMessage());
				throw new EmployeeDAOException(e.getMessage(), e);
			} finally {
				conn.setAutoCommit(true);
			}
			
		} catch(SQLException e) {
			LOGGER.log(Level.SEVERE, "Error while transferring employees to department " + newDepartment + " : " + e.getMessage());
			throw new EmployeeDAOException(e.getMessage(), e);
		}
	}
	
}


