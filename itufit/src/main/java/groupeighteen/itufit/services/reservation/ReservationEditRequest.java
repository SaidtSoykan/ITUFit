package groupeighteen.itufit.services.reservation;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class ReservationEditRequest {
    private Long facilityId;
    private Long userId;

    private Long oldId;
    private LocalDateTime newStartTime;
    private LocalDateTime newEndTime;
}