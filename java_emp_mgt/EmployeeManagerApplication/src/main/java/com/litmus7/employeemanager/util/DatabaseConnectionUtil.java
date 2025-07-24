package com.litmus7.employeemanager.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.litmus7.employeemanager.constant.DatabaseConnectionConstants;

public class DatabaseConnectionUtil {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DatabaseConnectionConstants.URL, DatabaseConnectionConstants.USER, DatabaseConnectionConstants.PASSWORD);
    }
}
