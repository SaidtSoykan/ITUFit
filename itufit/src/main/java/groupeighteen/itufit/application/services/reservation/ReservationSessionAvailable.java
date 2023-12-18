package groupeighteen.itufit.application.services.reservation;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationSessionAvailable {
    private Long facilityId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
