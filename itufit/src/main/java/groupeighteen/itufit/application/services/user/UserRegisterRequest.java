package groupeighteen.itufit.application.services.user;

import lombok.Getter;

@Getter
public class UserRegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
