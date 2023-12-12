import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WeeklyExerciseProgram {

    private int programId;
    private int reservationId;
    private LocalDate programDate;
    private LocalDate programFinishDate;
    private int studentId;
    private int totalCalories; 

    public WeeklyExerciseProgram(int programId, int reservationId, LocalDate programDate, int studentId,LocalDate programFinishDate) {
        this.programId = programId;
        this.reservationId = reservationId;
        this.programDate = programDate;
        this.programFinishDate = programFinishDate;
        this.studentId = studentId;
        this.totalCalories = calculateWeeklyCalories(); // Haftalık kaloriler
    }

    private int calculateWeeklyCalories() {

        Connection connection = DatabaseConnector.getConnection();
        String sql = "SELECT SUM(calories) AS total_calories FROM meal WHERE menu_date BETWEEN ? AND ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setDate(1, java.sql.Date.valueOf(programDate));
            preparedStatement.setDate(2, java.sql.Date.valueOf(programFinishDate));
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    
                    return resultSet.getInt("total_calories");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; 
    }

    public int getTotalCalories() {
        return totalCalories;
    }

    @Override
    public String toString() {
        return "WeeklyExerciseProgram{" +
                "programId=" + programId +
                ", reservationId=" + reservationId +
                ", programDate=" + programDate +
                ", studentId=" + studentId +
                ", totalCalories=" + totalCalories +
                '}';
    }
}
