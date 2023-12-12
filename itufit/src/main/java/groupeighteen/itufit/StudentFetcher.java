import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentFetcher {

    public static Student fetchStudent(Connection connection, String username, String password) {
        String sql = "SELECT * FROM students WHERE username = ? AND password = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Student student = new Student();
                    student.setStudentId(resultSet.getInt("student_id"));
                    student.setName(resultSet.getString("name"));
                    student.setSurname(resultSet.getString("surname"));
                    student.setGender(resultSet.getString("gender"));
                    student.setMatchedStudentId(resultSet.getInt("matched_student_id"));
                    student.setRestrictionViolationScore(resultSet.getInt("restriction_violation_score"));
                    student.setPassword(resultSet.getString("password"));
                    student.setEmail(resultSet.getString("email"));
                    student.setWeight(resultSet.getDouble("weight"));
                    student.setGoalWeight(resultSet.getDouble("goal_weight"));
                    return student;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; 
    }
}
