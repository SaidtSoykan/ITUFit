package groupeighteen.itufit.application.services.facility;

import groupeighteen.itufit.application.shared.response.IResponse;
import groupeighteen.itufit.domain.facility.Facility;

public interface FacilityService {
    public IResponse add(FacilityAddRequest facilityAddRequest);

    public IResponse remove(FacilityRemoveRequest facilityRemoveRequest);

    public Facility findById(Long id);
}