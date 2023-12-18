package groupeighteen.itufit.application.services.reservation;

import groupeighteen.itufit.application.persistence.repositories.ReservationRepository;
import groupeighteen.itufit.application.services.facility.FacilityService;
import groupeighteen.itufit.application.services.user.UserService;
import groupeighteen.itufit.domain.reservation.Reservation;

import java.util.List;

public class ReservationServiceImp implements ReservationService {
    private ReservationRepository reservationRepository;
    private FacilityService facilityService;
    private UserService userService;
    
    public ReservationServiceImp(ReservationRepository reservationRepository, FacilityService facilityService){
        this.reservationRepository = reservationRepository;
        this.facilityService = facilityService;
        this.userService = userService;
    }

    public boolean make(ReservationMakeRequest reservationMakeRequest) throws Exception{

        List<Reservation> reservations = reservationRepository.findByStartTimeAndFacilityId(reservationMakeRequest.getStartTime(), reservationMakeRequest.getFacilityId());
        if(reservations.isEmpty())
            throw new Exception("");
        
        Integer capacity = facilityService.findById(reservationMakeRequest.getFacilityId()).getCapacity();
        if(reservations.size() >= capacity)
            throw new Exception("");
        
        Boolean userIsRestricted = userService.findById(reservationMakeRequest.getUserId()).getIsRestricted();
        if(userIsRestricted)
            throw new Exception("");
        
        Reservation reservationToMake = new Reservation();
        reservationToMake.setStartTime(reservationMakeRequest.getStartTime());
        reservationToMake.setEndTime(reservationMakeRequest.getEndTime());
        reservationToMake.setFacility(facilityService.findById(reservationMakeRequest.getFacilityId()));
        reservationToMake.setUser(userService.findById(reservationMakeRequest.getUserId()));

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

    public void sessionAvailable(ReservationSessionAvailable reservationSessionAvailable) throws Exception{
        List<Reservation> reservations = reservationRepository.findByStartTimeAndFacilityId(reservationSessionAvailable.getStartTime(), reservationSessionAvailable.getFacilityId());
        if(reservations.isEmpty())
            throw new Exception("");
        
        Integer capacity = facilityService.findById(ReservationSessionAvailable.getFacilityId()).getCapacity();
        if(reservations.size() >= capacity)
            throw new Exception("");        
        
    }
}

