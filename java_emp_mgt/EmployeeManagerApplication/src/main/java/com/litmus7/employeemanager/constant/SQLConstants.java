package com.litmus7.employeemanager.constant;

public class SQLConstants {
	public static final String EMPLOYEE_INSERT = "INSERT INTO employee VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
	public static final String EMPLOYEE_SELECT_BY_ID = "SELECT * FROM employee WHERE emp_id = ?;";
	public static final String ALL_EMPLOYEE_SELECT = "SELECT * FROM employee;";
	public static final String CHECK_EMPLOYEE_BY_ID = "SELECT 1 FROM employee where emp_id = ?;";
}
