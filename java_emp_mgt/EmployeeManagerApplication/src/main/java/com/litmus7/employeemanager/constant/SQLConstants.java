package com.litmus7.employeemanager.constant;

public class SQLConstants {
	public static final String ADD_EMPLOYEE = "INSERT INTO employee VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
	public static final String GET_EMPLOYEE_BY_ID = "SELECT emp_id, first_name, last_name, email, phone, department, salary, join_date FROM employee WHERE emp_id = ?;";
	public static final String GET_EMPLOYEES = "SELECT emp_id, first_name, last_name, email, phone, department, salary, join_date FROM employee;";
}
