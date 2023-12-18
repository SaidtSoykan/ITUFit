package groupeighteen.itufit.application.services.reservation;

// import groupeighteen.itufit.domain.reservation.Reservation;

public interface ReservationService {
    public boolean make(ReservationMakeRequest reservationMakeRequest) throws Exception;
    public void delete(ReservationDeleteRequest reservationDeleteRequest) throws Exception;
    public void edit(ReservationEditRequest reservationEditRequest) throws Exception;
}
