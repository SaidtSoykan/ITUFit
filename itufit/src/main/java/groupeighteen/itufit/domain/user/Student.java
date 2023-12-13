package groupeighteen.itufit.domain.user;

import jakarta.persistence.Entity;

@Entity
public class Student extends User {
    private double weight;
    private double goalWeight;
    private double height;
    private Gender gender;
    private boolean isRestricted;
}
