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
        this.totalCalories = calculateWeeklyCalories(); 
    }

private void fetchWeeklyExerciseProgramDetails() {
    Connection connection = DatabaseConnector.getConnection();
    String caloriesSql = "SELECT SUM(calories) AS total_calories FROM meal WHERE menu_date BETWEEN ? AND ?";
    String programSql = "SELECT program_id, reservation_id, program_date, program_finish_date, student_id FROM weekly_exercise_program WHERE program_id = ?";

    try {
        try (PreparedStatement caloriesStatement = connection.prepareStatement(caloriesSql)) {
            caloriesStatement.setDate(1, java.sql.Date.valueOf(programDate));
            caloriesStatement.setDate(2, java.sql.Date.valueOf(programFinishDate));
            try (ResultSet caloriesResultSet = caloriesStatement.executeQuery()) {
                if (caloriesResultSet.next()) {
                    totalCalories = caloriesResultSet.getInt("total_calories");
                }
            }
        }
        try (PreparedStatement programStatement = connection.prepareStatement(programSql)) {
            programStatement.setInt(1, programId); 
            try (ResultSet programResultSet = programStatement.executeQuery()) {
                if (programResultSet.next()) {
                    programId = programResultSet.getInt("program_id");
                    reservationId = programResultSet.getInt("reservation_id");
                    programDate = programResultSet.getDate("program_date").toLocalDate();
                    programFinishDate = programResultSet.getDate("program_finish_date").toLocalDate();
                    studentId = programResultSet.getInt("student_id");
                }
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
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
