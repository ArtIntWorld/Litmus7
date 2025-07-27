package com.litmus7.employeemanager.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.litmus7.employeemanager.property.DatabaseConfiguration;

public class DatabaseConnectionUtil {
    public static Connection getConnection() throws SQLException {
        DatabaseConfiguration config = DatabaseConfiguration.getDatabaseConfiguration();
    	return DriverManager.getConnection(config.getURL(), config.getUser(), config.getPassword());
    }
}
