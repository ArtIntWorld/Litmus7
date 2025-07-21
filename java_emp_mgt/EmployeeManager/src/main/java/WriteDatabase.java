import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class WriteDatabase {
    public static void writeDB(){
        ReadCSV read = new ReadCSV();
        Connection conn = GetConnection.GetConnect();

        String filename = "D:\\Litmus7\\java_emp_mgt\\EmployeeManager\\src\\main\\java\\file\\Employee.csv";

        List<String[]> data = read.readCSV(filename);

        try {
            String sql = "INSERT INTO employee (emp_id, first_name, last_name, email, phone, department, salary, join_date) VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);

            for (String[] row : data) {
                Integer id = Integer.parseInt(row[0]);
                String first_name = row[1];
                String last_name = row[2];
                String email = row[3];
                String phone = row[4];
                String department = row[5];
                double salary = Double.parseDouble(row[6]);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate date = LocalDate.parse(row[7], formatter);

                ps.setInt(1, id);
                ps.setString(2, first_name);
                ps.setString(3, last_name);
                ps.setString(4, email);
                ps.setString(5, phone);
                ps.setString(6, department);
                ps.setDouble(7, salary);
                ps.setDate(8, Date.valueOf(date));
                ps.addBatch(); 
            }

            ps.executeBatch();
            ps.close();
            
            System.out.println("Data inserted successfully!");
        } catch (SQLException e) {
            System.err.println("Write SQL Error: " + e.getMessage());
        }
    }
}
