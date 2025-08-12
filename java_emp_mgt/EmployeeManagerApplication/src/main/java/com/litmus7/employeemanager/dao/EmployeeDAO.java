package com.litmus7.employeemanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.litmus7.employeemanager.constant.SQLConstants;
import com.litmus7.employeemanager.dto.EmployeeDTO;
import com.litmus7.employeemanager.exception.EmployeeDAOException;
import com.litmus7.employeemanager.util.DatabaseConnectionUtil;

public class EmployeeDAO {
	private static final Logger logger = LogManager.getLogger(EmployeeDAO.class);

	public EmployeeDTO getEmployeeByID(int id) throws EmployeeDAOException {
		
		logger.trace("Entering getEmployeeByID() with id : {}.", id);
		EmployeeDTO employee = null;
		try(Connection conn = DatabaseConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(SQLConstants.GET_EMPLOYEE_BY_ID)){
			
			logger.info("Database Connection was succesfully executed");
			logger.info("Statement preparation of query {} is executed.", SQLConstants.GET_EMPLOYEE_BY_ID);
			
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
				
			} catch(SQLException e){
				logger.error("Error while executing query for id {} : {}", id, e.getMessage());
				throw new EmployeeDAOException(e.getMessage(), e);
			}
			
			logger.trace("Exiting getEmployeeByID() with result {}.", employee);
			return employee;
			
		} catch(SQLException e){
			logger.error("Error in either database connection or statement preparation : {}.", e.getMessage());
			throw new EmployeeDAOException(e.getMessage(), e);
		}
		
	}
	
	public List<EmployeeDTO> getEmployees() throws EmployeeDAOException {
		
		logger.trace("Entering getEmployees().");
		List<EmployeeDTO> employees = new ArrayList<>();
		
		try(Connection conn = DatabaseConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(SQLConstants.GET_EMPLOYEES)){
			
			logger.info("Database Connection was succesfully executed");
			
			try(ResultSet rs = ps.executeQuery()){
				
				logger.info("Statement preparation of query {} is executed.", SQLConstants.GET_EMPLOYEES);
				
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
				
			} catch(SQLException e) {
				logger.error("Error while executing query: {}.", e.getMessage());
				throw new EmployeeDAOException(e.getMessage(), e);
			}
			
			logger.trace("Exiting getEmployees() with {} employees retrieved.", employees.size());
			return employees;
			
		} catch(SQLException e) {
			logger.error("Error in either database connection or statement preparation : {}.", e.getMessage());
			throw new EmployeeDAOException(e.getMessage(), e);
		}
	}
	
	public boolean saveEmployee(EmployeeDTO employee) throws EmployeeDAOException {
		
		logger.trace("Entering saveEmployee().");
		
		Integer id = employee.getID();
		String firstName = employee.getFirstName();
		String lastName = employee.getLastName();
		String email = employee.getEmail();
		String phone = employee.getPhone();
		String department = employee.getDepartment();
		double salary = employee.getSalary();
		Date joinDate = employee.getJoinDate();
		
		logger.debug("The employee details to be saved : ID: {}, First name: {}, Last name: {}, Email: {}, Phone: {}, Department: {}, Salary: {}, Join date: {}.", id, firstName, lastName, email, phone, department, salary, joinDate);
		
		try(Connection conn = DatabaseConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(SQLConstants.ADD_EMPLOYEE);) {
		
			logger.info("Database Connection was succesfully executed");
			logger.info("Statement preparation of query {} is executed.", SQLConstants.ADD_EMPLOYEE);
			
		    ps.setInt(1, id);
	        ps.setString(2, firstName);
	        ps.setString(3, lastName);
	        ps.setString(4, email);
	        ps.setString(5, phone);
	        ps.setString(6, department);
	        ps.setDouble(7, salary);
	        ps.setDate(8, joinDate);
	        
	        int rowsInserted = ps.executeUpdate();
	        
	        logger.trace("Exiting getEmployees() with {} employees added.", rowsInserted);
	        return rowsInserted > 0;

		} catch(SQLException e) {
			logger.error("Error in either database connection or statement preparation : {}.", e.getMessage());
			throw new EmployeeDAOException(e.getMessage(), e);
		}
	}
	
	public int deleteEmployee(int id) throws EmployeeDAOException {
		
		logger.trace("Entering deleteEmployee() with id {}.", id);
		try(Connection conn = DatabaseConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(SQLConstants.DELETE_EMPLOYEE)){
			
			logger.info("Database Connection was succesfully executed");
			logger.info("Statement preparation of query {} is executed.", SQLConstants.DELETE_EMPLOYEE);
			
			int rowsDeleted = ps.executeUpdate();
			
			logger.trace("Exiting getEmployees() with {} employees deleted.", rowsDeleted);
			return rowsDeleted;
			
		}catch(SQLException e) {
			logger.error("Error in either database connection or statement preparation : {}.", e.getMessage());
			throw new EmployeeDAOException(e.getMessage(), e);
		}
	}
	
	public int updateEmployee(EmployeeDTO employee) throws EmployeeDAOException {
		
		logger.trace("Entering updateEmployee().");
		logger.debug("The employee details to be saved : ID: {}, First name: {}, Last name: {}, Email: {}, Phone: {}, Department: {}, Salary: {}, Join date: {}.", employee.getID(), employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getPhone(), employee.getDepartment(), employee.getSalary(), employee.getJoinDate());
		
		try(Connection conn = DatabaseConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(SQLConstants.UPDATE_EMPLOYEE)){
			
			logger.info("Database Connection was succesfully executed.");
			logger.info("Statement preparation of query {} is executed.", SQLConstants.UPDATE_EMPLOYEE);
			
			ps.setInt(8, employee.getID());
			ps.setString(1, employee.getFirstName());
			ps.setString(2, employee.getLastName());
			ps.setString(3, employee.getEmail());
			ps.setString(4, employee.getPhone());
			ps.setString(5, employee.getDepartment());
			ps.setDouble(6, employee.getSalary());
			ps.setDate(7, employee.getJoinDate());
			
			int rowsAffected = ps.executeUpdate();
			
			logger.trace("Exiting updateEmployees() with {} employees updated.", rowsAffected);
			return rowsAffected;
			
		}catch(SQLException e) {
			logger.error("Error in either database connection or statement preparation : {}.", e.getMessage());
			throw new EmployeeDAOException(e.getMessage(), e);
		}
	}
	
	public int addEmployeesInBatch(List<EmployeeDTO> employees) throws EmployeeDAOException {
		
		logger.trace("Entering addEmployeeInBatch() with parameter {} employee data.", employees.size());
		
		int rowsAdded = 0;
		
		try (Connection conn = DatabaseConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(SQLConstants.ADD_EMPLOYEE);){
			
			logger.info("Database Connection was succesfully executed.");
			logger.info("Statement preparation of query {} is executed.", SQLConstants.ADD_EMPLOYEE);
			
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
			
			logger.trace("Exiting addEmployeesInBatch() with {} employees added.", rowsAdded);
			ps.executeBatch();
			
			return rowsAdded;
			
		} catch(SQLException e) {
			logger.error("Error in either database connection or statement preparation : {}.", e.getMessage());
			throw new EmployeeDAOException(e.getMessage(), e);
		}
	}
	
	public int transferEmployeesToDepartment(List<Integer> employeeIds, String newDepartment) throws EmployeeDAOException{
		
		int rowsAffected = 0;
		logger.trace("Entering transferEmployeesToDepartment() with parameter {} employee Ids and {} as new department.", employeeIds.size(), newDepartment);
		
		try(Connection conn = DatabaseConnectionUtil.getConnection()){
			
			logger.info("Database Connection was succesfully executed.");
			conn.setAutoCommit(false);
			
			try (PreparedStatement ps = conn.prepareStatement(SQLConstants.TRANSFER_TO_DEPARTMENT)){
				
				logger.info("Statement preparation of query {} is executed.", SQLConstants.TRANSFER_TO_DEPARTMENT);
				
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
				
				logger.trace("Exiting transferEmployeesToDepartment() with {} employee updated with department {}.", rowsAffected, newDepartment);
				return rowsAffected;
				
			}catch(SQLException e) {
				
				conn.rollback();
				logger.error("Error in statement preparation : {}.", e.getMessage());
				throw new EmployeeDAOException(e.getMessage(), e);
				
			} finally {
				conn.setAutoCommit(true);
			}
			
		} catch(SQLException e) {
			logger.error("Error in either database connection : {}.", e.getMessage());
			throw new EmployeeDAOException(e.getMessage(), e);
		}
	}
	
}


