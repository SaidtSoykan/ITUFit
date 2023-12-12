import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationFetcher {
    public static void main(String[] args) {

        Connection connection = DatabaseConnector.getConnection();

        String sql = "SELECT reservation_id, student_id, start_time, end_time, facility_id FROM reservations";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Reservation reservation = new Reservation(
                        resultSet.getInt("reservation_id"),
                        resultSet.getInt("student_id"),
                        resultSet.getTimestamp("start_time").toLocalDateTime(),
                        resultSet.getTimestamp("end_time").toLocalDateTime(),
                        resultSet.getInt("facility_id")
                );
                System.out.println(reservation.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

class Reservation {
    private int reservationId;
    private int studentId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int facilityId;

    public Reservation(int reservationId, int studentId, LocalDateTime startTime, LocalDateTime endTime, int facilityId) {
        this.reservationId = reservationId;
        this.studentId = studentId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.facilityId = facilityId;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", studentId=" + studentId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", facilityId=" + facilityId +
                '}';
    }
}
