import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignInService {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/your_database_name";
    private static final String USERNAME = "your_username";
    private static final String PASSWORD = "your_password";

    private static final String HASH_ALGORITHM = "SHA-256";

    public boolean addUser(String username, String password) {
        try {
            if (isUsernameTaken(username)) {
                System.out.println("Bu kullanıcı adı zaten alınmış. Başka bir kullanıcı adı girin.");
                return false;
            }
            String hashedPassword = hashPassword(password);
            try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
                String sql = "INSERT INTO users (username, password) VALUES (?, ?)";

                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, hashedPassword);

                    int affectedRows = preparedStatement.executeUpdate();
                    return affectedRows > 0;
                }
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isUsernameTaken(String username) throws SQLException {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, username);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt(1) > 0; 
                    }
                }
            }
        }
        return true; 
    }
    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(HASH_ALGORITHM);
        byte[] hashedBytes = messageDigest.digest(password.getBytes());
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : hashedBytes) {
            stringBuilder.append(String.format("%02x", b));
        }
        return stringBuilder.toString();
    }
    public static void main(String[] args) {
        SignInService signInService = new SignInService();
        boolean success = signInService.addUser("example_user", "example_password");

        if (success) {
            System.out.println("Kullanıcı başarıyla eklenmiştir.");
        } else {
            System.out.println("Kullanıcı eklenirken bir hata oluştu.");
        }
    }
}
