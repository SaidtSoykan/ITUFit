package groupeighteen.itufit.application.services.user.student.register;

import lombok.Getter;

@Getter
public class StudentRegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
