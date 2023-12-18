package groupeighteen.itufit.application.services.facility;

// import java.time.LocalDateTime;
// import java.time.LocalTime;


import org.springframework.stereotype.Service;
// import java.time.LocalDateTime;

import groupeighteen.itufit.application.persistence.repositories.FacilityRepository;
import groupeighteen.itufit.application.shared.response.IResponse;
import groupeighteen.itufit.application.shared.response.Response;
import groupeighteen.itufit.domain.facility.Facility;
// import groupeighteen.itufit.domain.user.User;

@Service
public class FacilityServiceImp implements FacilityService{
    private FacilityRepository facilityRepository;
    

    public FacilityServiceImp(FacilityRepository facilityRepository){
        this.facilityRepository = facilityRepository;
    }

    public IResponse add(FacilityAddRequest facilityAddRequest){
        var optionalFacility = facilityRepository.findByFacilityType(facilityAddRequest.getFacilityType());
        if(optionalFacility.isPresent())
            throw new RuntimeException("");
        
        Facility facilityToAdd = new Facility();

        facilityToAdd.setCapacity(facilityAddRequest.getCapacity());
        facilityToAdd.setDescription(facilityAddRequest.getDescription());
        facilityToAdd.setFacilityType(facilityAddRequest.getFacilityType());
        facilityToAdd.setLocation(facilityAddRequest.getLocation());

        facilityRepository.save(facilityToAdd);
        return new Response<>(true, "");
    }

    public IResponse remove(FacilityRemoveRequest facilityRemoveRequest){
        var optionalFacility = facilityRepository.findById(facilityRemoveRequest.getId());
        if(optionalFacility.isEmpty())
            throw new RuntimeException("");

        Facility facilityToRemove = optionalFacility.get();

        facilityRepository.delete(facilityToRemove);
        return new Response<>(true, "");
    }

    

    public Facility findById(Long id) {
        var optionalFacility = facilityRepository.findById(id);
        if(optionalFacility.isEmpty())
            throw new RuntimeException();
        return optionalFacility.get();
    }
}
