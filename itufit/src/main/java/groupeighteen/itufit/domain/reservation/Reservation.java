package groupeighteen.itufit.domain.reservation;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    //private Long userId; // toDO: Check if the user id type is correctly defined
    @OneToMany
    private User user;
    @OneToMany
    private Facility facility; // toDO: Check if the facility id type is correctly defined
}
