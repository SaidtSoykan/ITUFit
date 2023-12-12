import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FacilityFetcher {

    public static void main(String[] args) {
        Connection connection = DatabaseConnector.getConnection();

        String sql = "SELECT facility_id, facility_name, location, capacity, admin_id, restriction, description FROM facility";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Facility facility = new Facility(
                        resultSet.getInt("facility_id"),
                        resultSet.getString("facility_name"),
                        resultSet.getString("location"),
                        resultSet.getInt("capacity"),
                        resultSet.getInt("admin_id"),
                        resultSet.getString("restriction"),
                        resultSet.getString("description")
                );

                System.out.println(facility.toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

class Facility {
    private int facilityId;
    private String facilityName;
    private String location;
    private int capacity;
    private int adminId;
    private String restriction;
    private String description;

    public Facility(int facilityId, String facilityName, String location, int capacity, int adminId, String restriction, String description) {
        this.facilityId = facilityId;
        this.facilityName = facilityName;
        this.location = location;
        this.capacity = capacity;
        this.adminId = adminId;
        this.restriction = restriction;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Facility{" +
                "facilityId=" + facilityId +
                ", facilityName='" + facilityName + '\'' +
                ", location='" + location + '\'' +
                ", capacity=" + capacity +
                ", adminId=" + adminId +
                ", restriction='" + restriction + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}