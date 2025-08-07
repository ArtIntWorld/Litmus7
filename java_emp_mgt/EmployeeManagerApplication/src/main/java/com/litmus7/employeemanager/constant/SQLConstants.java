package com.litmus7.employeemanager.constant;

public class SQLConstants {
	public static final String ADD_EMPLOYEE = "INSERT INTO employee VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
	public static final String GET_EMPLOYEE_BY_ID = "SELECT emp_id, first_name, last_name, email, phone, department, salary, join_date FROM employee WHERE emp_id = ?;";
	public static final String GET_EMPLOYEES = "SELECT emp_id, first_name, last_name, email, phone, department, salary, join_date FROM employee;";
	public static final String DELETE_EMPLOYEE = "DELETE FROM employee WHERE emp_id = ?;";
	public static final String UPDATE_EMPLOYEE = "UPDATE employee SET first_name = ?, last_name = ? , email = ?, phone = ?, department = ?, salary = ?, join_date = ? WHERE emp_id = ?;";
	public static final String TRANSFER_TO_DEPARTMENT = "UPDATE employee SET department = ? WHERE emp_id = ?;";
}
