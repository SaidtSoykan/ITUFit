package groupeighteen.itufit.webapi;

import groupeighteen.itufit.application.services.user.student.login.StudentLoginRequest;
import groupeighteen.itufit.application.services.user.student.login.StudentLoginResponse;
import groupeighteen.itufit.application.services.user.student.register.StudentRegisterRequest;
import groupeighteen.itufit.application.services.user.student.StudentService;
import groupeighteen.itufit.application.shared.response.IDataResponse;
import groupeighteen.itufit.application.shared.response.IResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(value = "login", produces = "application/json")
    public IDataResponse<StudentLoginResponse> login(@RequestBody StudentLoginRequest studentLoginRequest) throws Exception {
        return studentService.login(studentLoginRequest);
    }

    @PostMapping(value = "register", produces = "application/json")
    public IResponse register(@RequestBody StudentRegisterRequest studentRegisterRequest) throws Exception {
        return studentService.register(studentRegisterRequest);
    }
}