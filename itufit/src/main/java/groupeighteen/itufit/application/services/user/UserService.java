package groupeighteen.itufit.application.services.user;

import groupeighteen.itufit.application.persistence.repositories.UserRepository;
import groupeighteen.itufit.application.security.HashService;
import groupeighteen.itufit.application.security.JwtService;
import groupeighteen.itufit.domain.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private HashService hashService;
    private JwtService jwtService;

    public UserService(UserRepository userRepository, HashService hashService, JwtService jwtService) {
        this.userRepository = userRepository;
        this.hashService = hashService;
        this.jwtService = jwtService;
    }

    public String login(UserLoginRequest userLoginRequest) throws Exception {
        var optionalUser = userRepository.findByEmail(userLoginRequest.getEmail());
        if (optionalUser.isEmpty())
            throw new Exception();
        var user = optionalUser.get();
        var hashedPassword = hashService.hashPassword(userLoginRequest.getPassword());
        if (hashedPassword.equals(user.getPassword123())){
            return jwtService.generateToken(user.getEmail());
        }
        throw new Exception("");
    }

    public void register(UserRegisterRequest userRegisterRequest) throws Exception {
        var optionalUser = userRepository.findByEmail(userRegisterRequest.getEmail());
        if (optionalUser.isPresent())
            throw new Exception("");

        User userToRegister = new User();
        userToRegister.setEmail(userRegisterRequest.getEmail());
        userToRegister.setFirstName(userRegisterRequest.getFirstName());
        userToRegister.setLastName(userRegisterRequest.getLastName());
        userToRegister.setPassword123(hashService.hashPassword(userRegisterRequest.getPassword()));

        userRepository.save(userToRegister);
    }

    public User findByEmail(String email) throws Exception {
        var optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty())
            throw new Exception();
        return optionalUser.get();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            return this.findByEmail(email);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
