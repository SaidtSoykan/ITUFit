package groupeighteen.itufit.application.services.facility;

// import java.time.LocalDateTime;
// import java.time.LocalTime;


import org.springframework.stereotype.Service;
// import java.time.LocalDateTime;

import groupeighteen.itufit.application.persistence.repositories.FacilityRepository;
import groupeighteen.itufit.domain.facility.Facility;
// import groupeighteen.itufit.domain.user.User;

@Service
public class FacilityServiceImp implements FacilityService{
    private FacilityRepository facilityRepository;
    

    public FacilityServiceImp(FacilityRepository facilityRepository){
        this.facilityRepository = facilityRepository;
    }

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

    

    public Facility findById(Long id) throws Exception {
        var optionalFacility = facilityRepository.findById(id);
        if(optionalFacility.isEmpty())
            throw new Exception();
        return optionalFacility.get();
    }
}
