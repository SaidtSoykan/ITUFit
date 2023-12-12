import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/your_database_name";
    private static final String USERNAME = "your_username";
    private static final String PASSWORD = "your_password";

    private static Connection connection;
    private static Student currentStudent; 

    private DatabaseConnector() {
        
    }

 public static synchronized Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                createCurrentStudent();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Veritabanına bağlanırken bir hata oluştu.");
            }
        }
        return connection;
    }

    public static Student getCurrentStudent() {
        return currentStudent;
    }

    private static void createCurrentStudent() {
        currentStudent = StudentFetcher.fetchStudent(USERNAME, PASSWORD);
    }
}