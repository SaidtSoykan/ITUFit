package groupeighteen.itufit.webapi;

import org.springframework.web.bind.annotation.RestController;

import groupeighteen.itufit.application.services.facility.FacilityAddRequest;
import groupeighteen.itufit.application.services.facility.FacilityRemoveRequest;
import groupeighteen.itufit.application.services.facility.FacilityService;
import groupeighteen.itufit.application.shared.response.IResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("facilities")
public class FacilityController {
    
    private final FacilityService facilityService;

    public FacilityController(FacilityService facilityService) {
        this.facilityService = facilityService;
    }

    @PostMapping(value = "addFacility", produces = "application/json")
    public IResponse add(@RequestBody FacilityAddRequest facilityAddRequest) throws Exception{
        return facilityService.add(facilityAddRequest);
    }

    @PostMapping(value = "removeFacility", produces = "application/json")
    public IResponse remove(@RequestBody FacilityRemoveRequest facilityRemoveRequest) throws Exception{
        return facilityService.remove(facilityRemoveRequest);
    }
}
