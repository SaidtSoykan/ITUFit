import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MealFetcher {
    public static void main(String[] args) {
        Connection connection = DatabaseConnector.getConnection();

        String sql = "SELECT meal_id, meal_time, calories, menu_date FROM meal_menu";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Meal meal = new Meal(
                        resultSet.getInt("meal_id"),
                        resultSet.getTimestamp("meal_time").toLocalDateTime(),
                        resultSet.getInt("calories"),
                        resultSet.getDate("menu_date").toLocalDate()
                );

                System.out.println(meal.toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

class Meal {
    private int mealId;
    private LocalDateTime mealTime;
    private int calories;
    private LocalDate menuDate;

    public Meal(int mealId, LocalDateTime mealTime, int calories, LocalDate menuDate) {
        this.mealId = mealId;
        this.mealTime = mealTime;
        this.calories = calories;
        this.menuDate = menuDate;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "mealId=" + mealId +
                ", mealTime=" + mealTime +
                ", calories=" + calories +
                ", menuDate=" + menuDate +
                '}';
    }
}
