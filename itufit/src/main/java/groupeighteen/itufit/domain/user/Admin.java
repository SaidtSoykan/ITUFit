package groupeighteen.itufit.domain.user;

import jakarta.persistence.Entity;

@Entity
public class Admin extends User{
    private int facilityId;
}
