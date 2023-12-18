package groupeighteen.itufit.application.services.reservation;

import groupeighteen.itufit.application.persistence.repositories.ReservationRepository;
import groupeighteen.itufit.application.services.facility.FacilityService;
import groupeighteen.itufit.application.services.reservation.sessionAvailable.ReservationSessionAvailableRequest;
import groupeighteen.itufit.application.services.reservation.sessionAvailable.ReservationSessionAvailableResponse;
import groupeighteen.itufit.application.services.reservation.make.ReservationMakeRequest;
import groupeighteen.itufit.application.services.reservation.edit.ReservationEditRequest;
import groupeighteen.itufit.application.services.reservation.delete.ReservationDeleteRequest;
import groupeighteen.itufit.application.services.user.student.StudentService;
// import groupeighteen.itufit.application.services.user.student.login.StudentLoginResponse;
import groupeighteen.itufit.application.shared.response.DataResponse;
import groupeighteen.itufit.application.shared.response.IResponse;
import groupeighteen.itufit.application.shared.response.Response;
import groupeighteen.itufit.domain.reservation.Reservation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImp implements ReservationService {
    private ReservationRepository reservationRepository;
    private FacilityService facilityService;
    private StudentService studentService;
    
    public ReservationServiceImp(ReservationRepository reservationRepository, FacilityService facilityService){
        this.reservationRepository = reservationRepository;
        this.facilityService = facilityService;
        // this.studentService = studentService;
    }

    public DataResponse<Boolean> make(ReservationMakeRequest reservationMakeRequest){

        List<Reservation> reservations = reservationRepository.findByStartTimeAndFacilityId(reservationMakeRequest.getStartTime(), reservationMakeRequest.getFacilityId());
        if(reservations.isEmpty())
            throw new RuntimeException("");
        
        Integer capacity = facilityService.findById(reservationMakeRequest.getFacilityId()).getCapacity();
        if(reservations.size() >= capacity)
            throw new RuntimeException("");
        
        Boolean userIsRestricted = studentService.findById(reservationMakeRequest.getUserId()).isRestricted();
        if(userIsRestricted)
            throw new RuntimeException("");
        
        Reservation reservationToMake = new Reservation();
        reservationToMake.setStartTime(reservationMakeRequest.getStartTime());
        reservationToMake.setEndTime(reservationMakeRequest.getEndTime());
        reservationToMake.setFacility(facilityService.findById(reservationMakeRequest.getFacilityId()));
        reservationToMake.setStudent(studentService.findById(reservationMakeRequest.getUserId()));

        reservationRepository.save(reservationToMake);
        var response = new DataResponse<Boolean>(
                    true,
                    "",
                    true);

        return response;
    }

    public IResponse delete(ReservationDeleteRequest reservationDeleteRequest){

        var optionalReservation = reservationRepository.findById(reservationDeleteRequest.getId());
        if(optionalReservation.isEmpty())
            throw new RuntimeException("");
        
        Reservation reservationToDelete = optionalReservation.get(); 
        reservationRepository.delete(reservationToDelete);
        
        return new Response<>(true, "");
    }
    
    public IResponse edit(ReservationEditRequest reservationEditRequest){
        var optionalReservation = reservationRepository.findById(reservationEditRequest.getOldId());
        if(optionalReservation.isEmpty())
            throw new RuntimeException("");

        ReservationDeleteRequest reservationDeleteRequest = new ReservationDeleteRequest();
        reservationDeleteRequest.setId(reservationEditRequest.getOldId());
        
        ReservationMakeRequest reservationMakeRequest = new ReservationMakeRequest();
        reservationMakeRequest.setStartTime(reservationEditRequest.getNewStartTime());
        reservationMakeRequest.setEndTime(reservationEditRequest.getNewEndTime());
        reservationMakeRequest.setFacilityId(reservationEditRequest.getFacilityId());
        reservationMakeRequest.setUserId(reservationEditRequest.getUserId());

        
        if(make(reservationMakeRequest).getData())
            delete(reservationDeleteRequest);
        return new Response<>(true, "");
    }

    public DataResponse<ReservationSessionAvailableResponse> sessionAvailable(ReservationSessionAvailableRequest reservationSessionAvailableRequest){
        List<Reservation> reservations = reservationRepository.findByStartTimeAndFacilityId(reservationSessionAvailableRequest.getStartTime(), reservationSessionAvailableRequest.getFacilityId());
        if(reservations.isEmpty())
            throw new RuntimeException("");
        
        Integer capacity = facilityService.findById(reservationSessionAvailableRequest.getFacilityId()).getCapacity();
        Integer reservationCount = reservations.size();

        if(reservationCount > capacity)
            throw new RuntimeException("");
        
        Float occupancy = (float) reservationCount / capacity;
        Boolean isFull;

        if(occupancy == 1)
            isFull = true;
        else
            isFull = false;
        
        var response = new DataResponse<ReservationSessionAvailableResponse>(
                    true,
                    "",
                    new ReservationSessionAvailableResponse(occupancy, isFull));

        return response;
    }

}

