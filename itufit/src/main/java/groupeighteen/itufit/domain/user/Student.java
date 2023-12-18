package groupeighteen.itufit.domain.user;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Student extends User {
    private double weight;
    private double goalWeight;
    private double height;
    private Gender gender;
    private boolean isRestricted;
}
