import java.sql.Connection;
import java.sql.SQLException;

public class EmployeeManagerController {
    public static void main(String[] args) {
        
        try {
            Connection conn = GetConnection.GetConnect();

            WriteDatabase.writeDB();
            conn.close();

        } catch (SQLException e) {
            System.err.println("Begin SQL Error: " + e.getMessage());
        }
    }
}
