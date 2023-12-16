package groupeighteen.itufit.domain.facility;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Facility{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private FacilityType facilityType;
    private Integer capacity;
    private String description;
    private String location;
}
