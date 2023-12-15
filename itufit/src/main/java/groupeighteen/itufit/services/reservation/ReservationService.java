package groupeighteen.itufit.services.reservation;

import groupeighteen.itufit.domain.reservation.Reservation;
import groupeighteen.itufit.persistence.repositories.ReservationRepository;

import java.util.List;

public class ReservationService {
    private ReservationRepository reservationRepository;
    
    public ReservationService(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    public boolean make(ReservationMakeRequest reservationMakeRequest) throws Exception{

        List<Reservation> reservations = reservationRepository.findByStartTimeAndFacilityId(reservationMakeRequest.getStartTime(), reservationMakeRequest.getFacilityId());
        if(reservations.isEmpty())
            throw new Exception("");
        
        // toDo: below 300 should be the capacity of the relevant facility. Add it properly
        if(reservations.size() >= 300)
            throw new Exception("");
        
        // toDo: check if the user is banned and prevent if so
        
        Reservation reservationToMake = new Reservation();
        reservationToMake.setStartTime(reservationMakeRequest.getStartTime());
        reservationToMake.setEndTime(reservationMakeRequest.getEndTime());
        reservationToMake.setFacilityId(reservationMakeRequest.getFacilityId());
        reservationToMake.setUserId(reservationMakeRequest.getUserId());

        reservationRepository.save(reservationToMake);
        return true;
    }

    public void delete(ReservationDeleteRequest reservationDeleteRequest) throws Exception{

        var optionalReservation = reservationRepository.findById(reservationDeleteRequest.getId());
        if(optionalReservation.isEmpty())
            throw new Exception("");
        
        Reservation reservationToDelete = optionalReservation.get(); 
        reservationRepository.delete(reservationToDelete);
    }
    
    public void edit(ReservationEditRequest reservationEditRequest) throws Exception{

        var optionalReservation = reservationRepository.findById(reservationEditRequest.getOldId());
        if(optionalReservation.isEmpty())
            throw new Exception("");

        ReservationDeleteRequest reservationDeleteRequest = new ReservationDeleteRequest();
        reservationDeleteRequest.setId(reservationEditRequest.getOldId());
        
        ReservationMakeRequest reservationMakeRequest = new ReservationMakeRequest();
        reservationMakeRequest.setStartTime(reservationEditRequest.getNewStartTime());
        reservationMakeRequest.setEndTime(reservationEditRequest.getNewEndTime());
        reservationMakeRequest.setFacilityId(reservationEditRequest.getFacilityId());
        reservationMakeRequest.setUserId(reservationEditRequest.getUserId());

        if(make(reservationMakeRequest))
            delete(reservationDeleteRequest);
    }
}

