package groupeighteen.itufit.domain.user;

import groupeighteen.itufit.domain.comment.Comment;
import groupeighteen.itufit.domain.reservation.Reservation;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Student extends User {
    private double weight;
    private double goalWeight;
    private double height;
    private Gender gender;
    private boolean isRestricted;
    @OneToMany(mappedBy = "student")
    private List<Reservation> reservations;
    @OneToMany(mappedBy = "student")
    private List<Comment> comments;
}
