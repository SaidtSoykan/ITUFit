import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentFetcher {

    public static void main(String[] args) {
        Connection connection = DatabaseConnector.getConnection();
        String sql = "SELECT comment_id, student_id, facility_id, comment_text, comment_date FROM comment";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Comment comment = new Comment(
                        resultSet.getInt("comment_id"),
                        resultSet.getInt("student_id"),
                        resultSet.getInt("facility_id"),
                        resultSet.getString("comment_text"),
                        resultSet.getTimestamp("comment_date").toLocalDateTime()
                );
                System.out.println(comment.toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

class Comment {
    private int commentId;
    private int studentId;
    private int facilityId;
    private String commentText;
    private LocalDateTime commentDate;

    public Comment(int commentId, int studentId, int facilityId, String commentText, LocalDateTime commentDate) {
        this.commentId = commentId;
        this.studentId = studentId;
        this.facilityId = facilityId;
        this.commentText = commentText;
        this.commentDate = commentDate;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", studentId=" + studentId +
                ", facilityId=" + facilityId +
                ", commentText='" + commentText + '\'' +
                ", commentDate=" + commentDate +
                '}';
    }
}
