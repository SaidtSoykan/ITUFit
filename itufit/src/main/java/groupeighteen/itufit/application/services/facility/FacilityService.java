package groupeighteen.itufit.services.facility;

// import java.time.LocalDateTime;
// import java.time.LocalTime;


import org.springframework.stereotype.Service;
// import java.time.LocalDateTime;

import groupeighteen.itufit.domain.facility.Facility;
// import groupeighteen.itufit.domain.facility.FacilityType;
import groupeighteen.itufit.persistence.repositories.FacilityRepository;

@Service
public class FacilityService {
    private FacilityRepository facilityRepository;

    public FacilityService(FacilityRepository facilityRepository){
        this.facilityRepository = facilityRepository;
    }

    // public boolean facilityIsAvaliable(Long facilityId, LocalDateTime sessionStart){

    // }

    public void add(FacilityAddRequest facilityAddRequest) throws Exception{
        var optionalFacility = facilityRepository.findByFacilityType(facilityAddRequest.getFacilityType());
        if(optionalFacility.isPresent())
            throw new Exception("");
        
        Facility facilityToAdd = new Facility();

        facilityToAdd.setCapacity(facilityAddRequest.getCapacity());
        facilityToAdd.setDescription(facilityAddRequest.getDescription());
        facilityToAdd.setFacilityType(facilityAddRequest.getFacilityType());
        facilityToAdd.setLocation(facilityAddRequest.getLocation());

        facilityRepository.save(facilityToAdd);
    }

    public void remove(FacilityRemoveRequest facilityRemoveRequest) throws Exception{
        var optionalFacility = facilityRepository.findById(facilityRemoveRequest.getId());
        if(optionalFacility.isEmpty())
            throw new Exception("");

        Facility facilityToRemove = optionalFacility.get();

        facilityRepository.delete(facilityToRemove);
    }
    

    // public void isAvaliable(FacilityType facilityType, LocalDateTime startTime){
        
    //     // toDo: Restricted times are currently ignored. Fix it

    //     LocalTime startHour = startTime.toLocalTime();
    //     LocalTime dayStartHour = LocalTime.of(8,0,0), dayEndHour = LocalTime.of(18, 0, 0);

    //     if(startHour.isBefore(dayStartHour))
    //         throw new Exception("");
        
    //     if(startHour.isAfter(dayEndHour))
    //         throw new Exception("");

    //     var
    // }

}
