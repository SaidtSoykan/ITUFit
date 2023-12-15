package groupeighteen.itufit.services.reservation;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationMakeRequest {
    private Long facilityId;
    private Long userId;    
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
