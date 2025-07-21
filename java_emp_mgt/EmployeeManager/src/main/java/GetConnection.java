import java.sql.*;

public class GetConnection {
    public static Connection GetConnect() {
        String url = "jdbc:mysql://localhost:3306/db_employee_details";
        String username = "root";
        String password = "Abdu12344!";
        Connection conn = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
        }
        catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
        }
        catch (SQLException e) {
            System.err.println("Connect SQL Error: " + e.getMessage());
        }

        return conn;
    }
}
