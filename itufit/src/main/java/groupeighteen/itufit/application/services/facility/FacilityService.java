package groupeighteen.itufit.application.services.facility;

import groupeighteen.itufit.domain.facility.Facility;

public interface FacilityService {
    public void add(FacilityAddRequest facilityAddRequest) throws Exception;
    public void remove(FacilityRemoveRequest facilityRemoveRequest) throws Exception;
    public Facility findById(Long id) throws Exception;
}
