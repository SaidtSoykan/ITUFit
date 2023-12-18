package groupeighteen.itufit.application.services.user.student;

import groupeighteen.itufit.application.persistence.repositories.StudentRepository;
import groupeighteen.itufit.application.security.HashService;
import groupeighteen.itufit.application.security.JwtService;
import groupeighteen.itufit.application.services.user.student.login.StudentLoginRequest;
import groupeighteen.itufit.application.services.user.student.login.StudentLoginResponse;
import groupeighteen.itufit.application.services.user.student.register.StudentRegisterRequest;
import groupeighteen.itufit.application.shared.response.DataResponse;
import groupeighteen.itufit.application.shared.response.IDataResponse;
import groupeighteen.itufit.application.shared.response.IResponse;
import groupeighteen.itufit.application.shared.response.Response;
import groupeighteen.itufit.domain.user.Student;
import groupeighteen.itufit.domain.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class StudentServiceImp implements UserDetailsService, StudentService {
    private StudentRepository studentRepository;
    private HashService hashService;
    private JwtService jwtService;

    public StudentServiceImp(StudentRepository studentRepository, HashService hashService, JwtService jwtService) {
        this.studentRepository = studentRepository;
        this.hashService = hashService;
        this.jwtService = jwtService;
    }

    public IDataResponse<StudentLoginResponse> login(StudentLoginRequest studentLoginRequest) throws Exception {
        var optionalStudent = studentRepository.findByEmail(studentLoginRequest.getEmail());
        if (optionalStudent.isEmpty())
            throw new Exception();
        var student = optionalStudent.get();
        var hashedPassword = hashService.hashPassword(studentLoginRequest.getPassword(), student.getPasswordSalt());
        if (Arrays.equals(hashedPassword, student.getPasswordHash())) {
            var response = new DataResponse<StudentLoginResponse>(
                    true,
                    "",
                    new StudentLoginResponse(jwtService.generateToken(student.getEmail())));

            return response;
        }
        throw new Exception("");
    }

    public IResponse register(StudentRegisterRequest studentRegisterRequest) throws Exception {
        var optionalStudent = studentRepository.findByEmail(studentRegisterRequest.getEmail());
        if (optionalStudent.isPresent())
            throw new Exception("");

        Student studentToRegister = new Student();
        studentToRegister.setEmail(studentRegisterRequest.getEmail());
        studentToRegister.setUserName(extractUsername(studentRegisterRequest.getEmail()));
        studentToRegister.setFirstName(studentRegisterRequest.getFirstName());
        studentToRegister.setLastName(studentRegisterRequest.getLastName());
        studentToRegister.setPasswordSalt(hashService.saltPassword(studentRegisterRequest.getPassword()));
        studentToRegister.setPasswordHash(hashService.hashPassword(studentRegisterRequest.getPassword(), studentToRegister.getPasswordSalt()));

        studentRepository.save(studentToRegister);
        return new Response<>(true, "");
    }

    public User findByEmail(String email) throws Exception {
        var optionalStudent = studentRepository.findByEmail(email);
        if (optionalStudent.isEmpty())
            throw new Exception();
        return optionalStudent.get();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            return this.findByEmail(email);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String extractUsername(String email){
        return email.substring(0, email.indexOf("@"));
    }
}
