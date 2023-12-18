package groupeighteen.itufit.application.services.facility;

import groupeighteen.itufit.application.shared.response.IResponse;
import groupeighteen.itufit.domain.facility.Facility;

public interface FacilityService {
    public IResponse add(FacilityAddRequest facilityAddRequest) throws Exception;
    public IResponse remove(FacilityRemoveRequest facilityRemoveRequest) throws Exception;
    public Facility findById(Long id) throws Exception;
}