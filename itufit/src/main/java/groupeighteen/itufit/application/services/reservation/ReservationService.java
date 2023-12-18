package groupeighteen.itufit.application.services.reservation;

import groupeighteen.itufit.application.services.reservation.sessionAvailable.ReservationSessionAvailableRequest;
import groupeighteen.itufit.application.services.reservation.sessionAvailable.ReservationSessionAvailableResponse;
import groupeighteen.itufit.application.services.reservation.make.ReservationMakeRequest;
import groupeighteen.itufit.application.services.reservation.edit.ReservationEditRequest;
import groupeighteen.itufit.application.services.reservation.delete.ReservationDeleteRequest;
import groupeighteen.itufit.application.shared.response.DataResponse;
// import groupeighteen.itufit.application.shared.response.IDataResponse;
import groupeighteen.itufit.application.shared.response.IResponse;

// import groupeighteen.itufit.domain.reservation.Reservation;

public interface ReservationService {
    public DataResponse<Boolean> make(ReservationMakeRequest reservationMakeRequest) throws Exception;
    public IResponse delete(ReservationDeleteRequest reservationDeleteRequest) throws Exception;
    public IResponse edit(ReservationEditRequest reservationEditRequest) throws Exception;
    public DataResponse<ReservationSessionAvailableResponse> sessionAvailable(ReservationSessionAvailableRequest reservationSessionAvailableRequest) throws Exception;
    
}
