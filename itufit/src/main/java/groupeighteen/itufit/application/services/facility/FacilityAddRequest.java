package groupeighteen.itufit.services.facility;

import groupeighteen.itufit.domain.facility.FacilityType;
import lombok.Getter;

@Getter
public class FacilityAddRequest {
    private FacilityType facilityType;
    private Integer capacity;
    private String description;
    private String location;
}
